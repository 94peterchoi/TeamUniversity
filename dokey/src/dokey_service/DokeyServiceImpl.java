package dokey_service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dokey_persistence.DokeyDAO;
import dokey_persistence.DokeyDAOImpl;
import dokey_vo.BuylistVO;
import dokey_vo.GameVO;
import dokey_vo.PointVO;
import dokey_vo.ReviewVO;
import dokey_vo.UserinfoVO;
import dokey_vo.WishlistVO;

public class DokeyServiceImpl implements DokeyService {

	// 회원가입 아이디 중복확인 처리
	@Override
	public void confirmId(HttpServletRequest req, HttpServletResponse res) {
		String strId = req.getParameter("id");
		
		// 4단계. 싱글톤 방식으로 dao 객체 생성, 다형성 적용
		DokeyDAO dao = DokeyDAOImpl.getInstance();

		// 5단계. 중복확인 처리
		int cnt = dao.idCheck(strId);
		
		req.setAttribute("selectCnt", cnt);	//
		req.setAttribute("id", strId);
	}
	
	
	// 회원가입 처리 (고객이 가입양식에 입력한 정보 받아옴)
	@Override
	public void signinAction(HttpServletRequest req, HttpServletResponse res) {
		int insertCnt = 0;
		
		UserinfoVO vo = new UserinfoVO();
		
		vo.setId(req.getParameter("id"));
		vo.setPwd(req.getParameter("pwd"));
		vo.setNickname(req.getParameter("nickname"));
		vo.setHp(req.getParameter("hp"));
		vo.setEmail(req.getParameter("email"));
		vo.setReg_date(new Timestamp(System.currentTimeMillis()));
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		insertCnt = dao.insertUser(vo);
		
		req.setAttribute("insertCnt", insertCnt);
		
	}
	

	
	// 로그인 처리
	@Override
	public void loginAction(HttpServletRequest req, HttpServletResponse res) {
		
		int selectCnt = 0;
		
		String strId = req.getParameter("id");
		String strPwd = req.getParameter("pwd");
		
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		selectCnt = dao.idPwdCheck(strId, strPwd);
		
		// 로그인 성공 (고객 : 1)
		if (selectCnt == 1) {
			req.getSession().setAttribute("userId", strId);
		}
		
		// 로그인 성공 (매니져 : 7)
		if (selectCnt == 7) {
			req.getSession().setAttribute("managerId", strId);
		}
		
		req.setAttribute("selectCnt", selectCnt);
		
	}

	// 고객 본인 프로필 조회
	@Override
	public void profile(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		UserinfoVO dto = dao.profile(userid);
		
		req.setAttribute("dto", dto);
	}

	// 고객 본인 포인트 충전내역 조회 (프로필에서 조회가능)
	@Override
	public void viewPoint(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		int pageSize = 6;	// 한 페이지당 출력할 글 갯수
		int pageBlock = 5;	// 한 블럭당 페이지 갯수
		
		int cnt = 0;			// 글갯수
		int start = 0;			// 현재 페이지 시작 글번호
		int end = 0;			// 현재 페이지 마지막 글번호
		int number = 0;			// 출력용 글번호
		String pageNum = "";	// 페이지 번호
		int currentPage = 0;	// 현재페이지
		
		int pageCount = 0;		// 페이지 갯수
		int startPage = 0;		// 시작페이지
		int endPage = 0;		// 마지막 페이지

		cnt = dao.getPointCount(userid);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";	// 첫페이지를 1페이지로 지정
		}
		
		currentPage = Integer.parseInt(pageNum);	// 현재페이지 : 1
		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);	// 페이지 갯수 + 나머지 있으면 1페이지
	
		start = (currentPage - 1) * pageSize + 1;
		
		end = start + pageSize - 1;
		
		number = cnt - (currentPage - 1) * pageSize;
		
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		
		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		
		endPage = startPage + pageBlock -1;
		if(endPage > pageCount) endPage = pageCount;
		
		if(cnt > 0) {
			ArrayList<PointVO> dtos = dao.viewPoint(userid, start, end);
			req.setAttribute("dtos", dtos);
		}
		
		req.setAttribute("cnt", cnt);			// 글갯수
		req.setAttribute("number", number); 	// 출력용 글번호
		req.setAttribute("pageNum", pageNum);	// 페이지 번호
		
		if(cnt > 0) {
			req.setAttribute("startPage", startPage);// 시작페이지
			req.setAttribute("endPage", endPage);	// 마지막페이지
			req.setAttribute("pageBlock", pageBlock);	// 한 블럭당 페이지 갯수
			req.setAttribute("pageCount", pageCount);	// 페이지갯수
			req.setAttribute("currentPage", currentPage);	// 현재페이지
		}
		
		UserinfoVO dto = dao.profile(userid);
		req.setAttribute("dto", dto);

		
	}
	
	
	// 고객 포인트 충전하기
	@Override
	public void addPoint(HttpServletRequest req, HttpServletResponse res) {
		int updateCnt = 0;
		
		int point = Integer.parseInt(req.getParameter("point"));
		String userid = (String) req.getSession().getAttribute("userId");
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		updateCnt = dao.addPoint(userid, point);
		
				
		System.out.println("포인트충전 updateCnt 출력:" + updateCnt);
				
	}
	
	// 고객 본인 프로필(회원정보) 수정
	@Override
	public void updateProfile(HttpServletRequest req, HttpServletResponse res) {
		
		int selectCnt = 0;

		String strId = (String) req.getSession().getAttribute("userId");
		String strPwd = req.getParameter("pwd");

		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		selectCnt = dao.idPwdCheck(strId, strPwd);

		req.setAttribute("selectCnt", selectCnt);
		
	}

	
	// 고객 본인 프로필(회원정보) 수정 - 상세페이지
	@Override
	public void updateProfileAction(HttpServletRequest req, HttpServletResponse res) {
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		String userid = (String) req.getSession().getAttribute("userId");
		UserinfoVO vo = null;
		
		vo = dao.getUserInfo(userid);
		
		req.setAttribute("vo", vo);
		
	}
	
	// 고객 본인 프로필 수정 처리
	@Override
	public void updateProfileFinish(HttpServletRequest req, HttpServletResponse res) {
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		UserinfoVO vo = new UserinfoVO();
		int updateCnt = 0;
		
		String userid = (String) req.getSession().getAttribute("userId");
		
		vo.setId(userid);
		vo.setPwd(req.getParameter("pwd"));
		vo.setNickname(req.getParameter("nickname"));
		vo.setHp(req.getParameter("hp"));
		vo.setEmail(req.getParameter("email"));

		updateCnt  = dao.updateUserProfile(vo);
		System.out.println("updateCnt값 출력:" + updateCnt);
	}

	
	// 고객 회원탈퇴 - 회원탈퇴 처리 및 탈퇴기록 관리자 DB에 저장
	@Override
	public void deleteProfile(HttpServletRequest req, HttpServletResponse res) {
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		String userid = (String) req.getSession().getAttribute("userId");
		String reason = req.getParameter("reason");
		String suggestion = req.getParameter("suggestion");

		int deleteCnt = dao.deleteProfile(userid, reason, suggestion);
		req.setAttribute("deleteCnt", deleteCnt);
		
	}
	
	
	// 스토어 목록 전체 조회
	@Override
	public void readAll(HttpServletRequest req, HttpServletResponse res) {
		
		// 페이징
		int pageSize = 12;	// 한 페이지당 출력할 글 갯수
		int pageBlock = 5;	// 한 블럭당 페이지 갯수
		
		int cnt = 0;			// 글갯수
		int start = 0;			// 현재 페이지 시작 글번호
		int end = 0;			// 현재 페이지 마지막 글번호
		int number = 0;			// 출력용 글번호
		String pageNum = "";	// 페이지 번호
		int currentPage = 0;	// 현재페이지
		
		int pageCount = 0;		// 페이지 갯수
		int startPage = 0;		// 시작페이지
		int endPage = 0;		// 마지막 페이지
		
		// 유저가 입력한 장르 (우선 디폴트인 전체장르 보여주기 위해 %0%로 시작)
		String genre = req.getParameter("genre");
		
		int value = 0;
		// 게임 목록 기준별로 정렬하기
		if (req.getParameter("order") != null) {
			value = Integer.parseInt(req.getParameter("order"));
		}
		
		int order = 0;	// 전체게임(처음 스토어로 진입했을 때)은 order값 0
		
		if (value == 0) {
			System.out.println("신작기준");
			order = 0;
		} else if (value == 1) {
			System.out.println("고전작기준");
			order = 1;
		} else if (value == 2) {
			System.out.println("가격높은순");
			order = 2;
		} else if (value == 3) {
			System.out.println("가격낮은순");
			order = 3;
		}
		
		
		if (genre == null) {
			genre = "%0%";	// 처음에 스토어로 진입했을 땐 전체보여주기로 시작
		}
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		cnt = dao.getGenreCount(genre);
		
		pageNum = req.getParameter("pageNum");	// 상욱 - 페이지넘 어떻게 전달받아야 되지
				
		if (pageNum == null) {
			pageNum = "1";
		}
		
		currentPage = Integer.parseInt(pageNum);
		System.out.println("현재페이지 확인[currentPage] : " + currentPage + "페이지다람쥐.");
		
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		start = (currentPage - 1) * pageSize + 1;
		end = start + pageSize -1;
		
		System.out.println("현재페이지 시작글번호[start]: " + start);
		System.out.println("현재페이지 마지막 글번호[end]: " + end);
		
		
		number = cnt - (currentPage - 1) * pageSize;
		System.out.println("number: " + number);
		System.out.println("pageSize: " + pageSize);
		
		
		// 시작페이지
		startPage = (currentPage / pageBlock) * pageBlock + 1;

		if(currentPage % pageBlock == 0) startPage -= pageBlock;
		System.out.println("시작페이지[startPage] : " + startPage);

		
		// 마지막페이지
		endPage = startPage + pageBlock -1;
		
		if(endPage > pageCount) endPage = pageCount;

		System.out.println("마지막페이지[endPage] : " + endPage);

		System.out.println("pageCount: " + pageCount);
		System.out.println("pageBlock: " + pageBlock);

		
		if(cnt > 0) {
		// 5-2 단계. 게시글 목록 조회		
//			start = 1;
//			end = 150;
			
			ArrayList<GameVO> dtos = dao.getGenreList(genre, start, end, order);
			// 6 단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
			req.setAttribute("dtos", dtos);
		}
		
		// 6 단계. request나 session에 처리결과를 저장(jsp에 전달하기 위함)
		
		req.setAttribute("cnt", cnt);			// 글갯수
		req.setAttribute("number", number); 	// 출력용 글번호
		req.setAttribute("pageNum", pageNum);	// 페이지 번호
		
		if(cnt > 0) {
			req.setAttribute("startPage", startPage);// 시작페이지
			req.setAttribute("endPage", endPage);	// 마지막페이지
			req.setAttribute("pageBlock", pageBlock);	// 한 블럭당 페이지 갯수
			req.setAttribute("pageCount", pageCount);	// 페이지갯수
			req.setAttribute("currentPage", currentPage);	// 현재페이지
			req.setAttribute("genre", genre);	// 장르별
			req.setAttribute("order", order);	// 정렬기준
		}
		
	}


	// 게임 상세정보 조회
	@Override
	public void detail(HttpServletRequest req, HttpServletResponse res) {
		
//		int num = Integer.parseInt(req.getParameter("num"));
//		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
//		int number = Integer.parseInt(req.getParameter("number"));
		String userid = (String) req.getSession().getAttribute("userId");
		int code = Integer.parseInt(req.getParameter("code"));
		
		System.out.println("장바구니용 코드출력: " + code);
		int buyornot = 0;	// 구매하지 않은 게임이라면 0 , 구매한 게임이라면 1, 구매없이 장바구니에 담기만 했다면 2
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		// 조회수 증가
//		dao.increaseView(num);
		
		// 구매버튼하고 장바구니버튼 블락 (구매한 고객에 한해) 
//		if ((String) req.getSession().getAttribute("userId") != null) {
//			buyornot = dao.block(num, userid);
//			if (buyornot != 1) {
//				if(dao.picked(code, userid) == 1) {
//					buyornot = 2;
//				}
//			}
//			
//		}
		
		GameVO vo = dao.getGameDetail(code);
		
		req.setAttribute("dto", vo);
//		req.setAttribute("pageNum", pageNum);
//		req.setAttribute("number", number);
		req.setAttribute("buyornot", buyornot);
		
	}

	// 특정 게임 전체리뷰 조회
	@Override
	public void seeReview(HttpServletRequest req, HttpServletResponse res) {
		
		int code = Integer.parseInt(req.getParameter("code"));
		
		System.out.println("게임코드출력 : " + code);
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		ArrayList<ReviewVO> dtos = dao.seeReview(code);
		dtos.size();
		
		req.setAttribute("code", code);
		req.setAttribute("dtos", dtos);

	}

	// 고객 포인트 조회 (게임구매 화면)
	@Override
	public void checkPoint(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		int point = dao.checkPoint(userid);
		
		req.setAttribute("point", point);
		
	}

	// 고객 게임구매 (포인트로 게임 사기)
	@Override
	public void buyGame(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		int code = Integer.parseInt(req.getParameter("code"));
		
		System.out.println(userid);
		System.out.println(code);
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		int buyCnt = dao.buyGame(code, userid); // 고객 : 게임개수 +, 포인트 - || 게임 : 판매개수 + || 구매테이블에 고객 구매정보 추가
		
		req.setAttribute("buyCnt", buyCnt);
	}

	
	// 고객 구매이력 조회
	@Override
	public void seeBuylist(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		ArrayList<BuylistVO> dtos = dao.seeBuylist(userid);
		
		req.setAttribute("dtos", dtos);
	}

	// 고객 게임환불
	@Override
	public void refundGame(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		int code = Integer.parseInt(req.getParameter("code"));
		int price =  Integer.parseInt(req.getParameter("price"));
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();

		dao.refundGame(userid, code, price);
	}


	// 고객 장바구니 담기 버튼 클릭
	@Override
	public void wishlist(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		int code = Integer.parseInt(req.getParameter("code"));
		
		// 장바구니 담은 게임 url 설정
		String url = "";
		String num = req.getParameter("num");
		String pageNum = req.getParameter("pageNum");
		int number = Integer.parseInt(req.getParameter("number")) + 1;
		url = "detail.do?code=" + code + "&num=" + num + "&pageNum=" + pageNum + "&number=" + number;
		System.out.println("장바구니용 url출력 : "+ url);
		System.out.println("코드출력:: " + code);
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		int insertCnt = 0; 
		int selectCnt = dao.picked(code, userid);	// 해당 상품이 장바구니에 담겼는지 안담겼는지 확인하는 메서드 
		
		int resultCnt = 0;
		
		if (selectCnt == 0) {
			insertCnt = dao.wishlist(userid, code, url);
		} else {
			resultCnt = 1;
		}
		
		req.setAttribute("resultCnt", resultCnt);
		
	}

	// 고객 장바구니 보기
	@Override
	public void viewWishlist(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
		
		ArrayList<WishlistVO> dtos = dao.viewWishlist(userid);
		
		req.setAttribute("dtos", dtos);

	}
	
	
	// 고객 장바구니에서 상품 삭제
	@Override
	public void deleteWishlist(HttpServletRequest req, HttpServletResponse res) {
		
		String userid = (String) req.getSession().getAttribute("userId");
		int code = Integer.parseInt(req.getParameter("code"));

		DokeyDAO dao = DokeyDAOImpl.getInstance();

		dao.deleteWishlist(userid, code);
		
	}

	



	
}
