// 흠 ..
package dokey_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dokey_service.DokeyService;
import dokey_service.DokeyServiceImpl;


@WebServlet("*.do")
public class DokeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public DokeyController() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		action(req, res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doGet(req, res);
	}
	
	public void action(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		
		String viewPage = "";
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();
		String url = uri.substring(contextPath.length());
		DokeyService service = new DokeyServiceImpl();
		
		// 홈 화면
		if(url.equals("/home.do") || url.equals("/*.do")) {
			System.out.println("[url ==> home.do]");
			
			
			viewPage = "/dokey/home.jsp";
		
		// 로그인 화면으로 이동
		} else if (url.equals("/login.do")) {
			System.out.println("[url ==> login.do]");
			
			viewPage = "/dokey/login.jsp";
			
		// 회원가입 화면으로 이동 (양식 뿌려주기)
		} else if (url.equals("/signin.do")) {
			System.out.println("[url ==> signin.do]");
			
			viewPage = "/dokey/signin.jsp";
			
		// 회원가입 아이디 중복검사
		} else if (url.equals("/confirmId.do")) {
			System.out.println("[url ==> confirmId.do]");
			
			service.confirmId(req, res);
			viewPage = "/dokey/confirmId.jsp";
			
			
		// 회원가입 양식에서 고객이 입력한 정보 넘겨받기
		} else if (url.equals("/signinAction.do")) {
			System.out.println("[url ==> signinAction.do]");
			
			service.signinAction(req, res);

			int insertCnt = (Integer) req.getAttribute("insertCnt");
			req.setAttribute("insertCnt", insertCnt);
			
			System.out.println("insertCnt값(1일 경우 성공)--> " + insertCnt);
			viewPage = "/dokey/home.jsp";
			
			
		// 로그인 처리
		} else if (url.equals("/loginAction.do")) {
			System.out.println("[url ==> loginAction.do]");
			
			service.loginAction(req, res);
			
			int selectCnt = (Integer) req.getAttribute("selectCnt");
			req.setAttribute("selectCnt", selectCnt);
			
			
			if (selectCnt == 7) {
				viewPage = "/manager/home.jsp";
			} else if (selectCnt == 0 || selectCnt == -1) {
				viewPage = "/dokey/login.jsp";
			} else {
				viewPage = "/dokey/home.jsp";
			}
			
			
		// 로그아웃 처리
		} else if(url.equals("/logout.do")) {
			System.out.println("[url ==> /logout.do]");
			
			/* 세션 삭제
			 * req.getsession().invalidate();	// 모든 세션 삭제
			 * req.getSession.setAttribute("clientId", null);	// clientId라는 섹션 삭제
			 * req.getSession().removeAttribute("clientId"); // clientId라는 섹션 삭제
			 */
			
			req.getSession().removeAttribute("userId"); // userId 세션 삭제 (로그아웃) 
			
			viewPage = "/dokey/home.jsp";
			
		
		// 스토어 (전체보기 디폴트)
		} else if(url.equals("/store.do")) {
			System.out.println("[url ==> store.do]");
			
			service.readAll(req, res);
			
			viewPage = "/dokey/all.jsp";
			
			
		// 스토어 (장르별) - 위에 전체보기랑 똑같은 로직
		} else if (url.equals("/genre.do")) {
			System.out.println("[url ==> genre.do]");
			
			service.readAll(req, res);
			
			viewPage = "/dokey/all.jsp";
			
			
		// 게임 상세보기
		} else if (url.equals("/detail.do")) {
			System.out.println("[url ==> /detail.do]");
			
			service.detail(req, res);
			
			viewPage = "/dokey/detail.jsp";
			
			
		// 해당 게임 전체 리뷰 보기 (게임 상세페이지 -> 전체 리뷰보기)
		} else if (url.equals("/review.do")) {
			System.out.println("[url ==> /review.do]");
			
			service.seeReview(req, res);
			
			viewPage = "/dokey/review.jsp";
			
		// 고객 게임 구매 화면 (지금구매 버튼 클릭)
		} else if (url.equals("/buy.do")) {
			System.out.println("[url ==> /buy.do]");
			
			if(req.getSession().getAttribute("userId") != null) {
				req.setAttribute("code", req.getParameter("code"));
				req.setAttribute("title", req.getParameter("title"));
				req.setAttribute("publisher", req.getParameter("publisher"));
				req.setAttribute("gamelogo", req.getParameter("gamelogo"));
				req.setAttribute("price", req.getParameter("price"));
				
				// 고객 포인트 조회 메서드
				service.checkPoint(req, res);
				
				viewPage = "/user/buy.jsp";
				
			} else {
				
				viewPage = "/dokey/login.jsp"; 
			}

			
		// 고객 게임 구매 처리 (이거 화면 좀 보류하자)
		} else if (url.equals("/buyAction.do")) {
			System.out.println("[url ==> /buyAction.do]");
			
			viewPage = "";
			
			
		// 고객 게임 구매완료
		} else if (url.equals("/finishBuy.do")) {
			System.out.println("[url ==> /finishBuy.do");
			
			req.setAttribute("code", req.getParameter("code"));
			
			service.buyGame(req, res);
			
			System.out.println("buyCnt값: " + req.getAttribute("buyCnt"));
			
			viewPage = "/dokey/home.jsp";
		
		// 고객 구매이력 조회
		} else if (url.equals("/buylist.do")) {
			System.out.println("[url ==> /buylist.do]");
			
			service.seeBuylist(req, res);
			
			viewPage = "/user/buylist.jsp";
			
			
		// 고객 게임환불
		} else if (url.equals("/refundGame.do")) {
			System.out.println("[url ==> /refundGame.do]");
			
			service.refundGame(req, res);
			
			viewPage = "/dokey/home.jsp";
			
			
		// 고객 장바구니 담기 버튼 클릭
		} else if (url.equals("/wishlist.do")) {
			System.out.println("[url ==> /wishlist.do]");
			
			service.wishlist(req, res);
			
			viewPage = "/dokey/home.jsp";
		
			
		// 고객 장바구니 보기
		} else if (url.equals("/viewWishlist.do")) {
			System.out.println("[url ==> /viewWishlist.do]");
			
			service.viewWishlist(req, res);
			
			viewPage = "/user/wishlist.jsp";
		
			
		// 고객 장바구니에서 상품 삭제
		} else if (url.equals("/deleteWishlist.do")) {
			System.out.println("[url ==> /deleteWishlist.do]");
			
			service.deleteWishlist(req, res);
			service.viewWishlist(req, res);
			
			viewPage = "user/wishlist.jsp";
			
			
		// 고객 본인 프로필(회원정보) 조회 
		} else if (url.equals("/profile.do")) {
			System.out.println("[url ==> /profile.do]");
			
			service.profile(req, res);
			
			viewPage = "/user/profile.jsp";
			
		// 고객 포인트 충전 (포인트충전 화면으로 이동)
		} else if (url.equals("/addPoint.do")) {
			System.out.println("[url ==> /addPoint.do]");
			
			viewPage = "/user/addPoint.jsp";
			
		// 고객 포인트 충전 처리
		} else if (url.equals("/addPointAction.do")) {
			System.out.println("[url ==> /addPoint.do]");

			service.addPoint(req, res);
			service.profile(req, res);
			service.viewPoint(req, res);
			
			String point = req.getParameter("point");
			String cardname = req.getParameter("cardname");
			System.out.println("라디오에서 넘어온 포인트값: " + point);
			System.out.println("인풋 cardname 명의자: " + cardname);
			
			viewPage = "/user/viewPoint.jsp";
			
		// 고객 포인트 충전내역 조회 (프로필에서 조회가능)
		} else if (url.equals("/viewPoint.do")) {
			System.out.println("[url ==> /viewPoint.do]");
			
			// service.profile(req, res);	// 0226 상욱: 주석처리함.. 이게 왜 필요했지??
			service.viewPoint(req, res);
			
			viewPage = "/user/viewPoint.jsp";
		
		// 고객 본인 프로필(회원정보) 수정 - 인증화면
		} else if (url.equals("/updateProfile.do")) {
			System.out.println("[url ==> /profile.do]");
			
			viewPage = "/user/updateProfile.jsp"; 
					
		// 고객 본인 프로필(회원정보) 수정 상세페이지
		} else if (url.equals("/updateProfileView.do")) {
			
			service.updateProfile(req, res);
			
			int selectCnt =(Integer) req.getAttribute("selectCnt");
			
			if (selectCnt == -1) {
				viewPage = "/user/updateProfile.jsp";
			} else if (selectCnt == 1){
				service.updateProfileAction(req, res);
				viewPage = "/user/updateProfileView.jsp";
			}
			
		// 고객 본인 프로필(회원정보) 수정 처리
		} else if (url.equals("/updateProfileAction.do")) {
			System.out.println("[url ==> /updateProfileAction.do]");
			
			service.updateProfileFinish(req, res);
			service.profile(req, res);
			
			viewPage = "/user/profile.jsp";
			
			
		// 고객 회원탈퇴 - 비밀번호 인증화면
		} else if (url.equals("/deleteProfile.do")) {
			System.out.println("[url ==> /deleteProfile.do]");
			
			
			viewPage = "/user/deleteProfile.jsp"; 
		
		// 고객 회원탈퇴 - 회원탈퇴 사유 선택
		} else if (url.equals("/deleteProfileView.do")) {
			System.out.println("[url ==> /deleteProfileView.do]");

			service.updateProfile(req, res);	// 아이디, 비밀번호 체크용 (update 메서드 재사용)
			
			int selectCnt =(Integer) req.getAttribute("selectCnt");
			
			if (selectCnt == -1) {
				viewPage = "/user/deleteProfile.jsp";
			} else if (selectCnt == 1){
				viewPage = "/user/deleteProfileView.jsp";
			}

		// 고객 회원탈퇴 - 회원탈퇴 처리 및 탈퇴기록 관리자 DB에 저장
		} else if (url.equals("/deleteProfileAction.do")) {
			System.out.println("[url ==> /deleteProfileAction.do]");
			
			service.deleteProfile(req, res);
			
			int deleteCnt = (Integer) req.getAttribute("deleteCnt");
			System.out.println("deleteCnt 출력: " + deleteCnt);
			
			req.getSession().removeAttribute("userId"); // 세션삭제
			
			viewPage = "/dokey/home.jsp";
			
		}
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
		dispatcher.forward(req, res);
		
	}

}
