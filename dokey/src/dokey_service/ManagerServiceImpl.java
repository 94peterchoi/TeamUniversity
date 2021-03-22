package dokey_service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dokey_persistence.DokeyDAO;
import dokey_persistence.DokeyDAOImpl;
import dokey_persistence.ManagerDAO;
import dokey_persistence.ManagerDAOImpl;
import dokey_vo.GameVO;

public class ManagerServiceImpl implements ManagerService {
	
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
		if (genre == null) {
			genre = "%0%";	// 처음에 스토어로 진입했을 땐 전체보여주기로 시작
		}
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		
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
			
			ArrayList<GameVO> dtos = dao.getGenreList(genre, start, end);
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
			req.setAttribute("genre", genre);
		}
		
	}

	
	// 스토어 목록에서 게임 삭제
	@Override
	public void deleteGame(HttpServletRequest req, HttpServletResponse res) {
		
		int deleteCnt = 0;
		
		int code = Integer.parseInt(req.getParameter("codeValue"));
		
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		deleteCnt = dao.deleteGame(code);
		
		req.setAttribute("deleteCnt", deleteCnt);
		
	}


	// 스토어 목록에 게임 추가(등록)
	@Override
	public void insertGame(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		int sizeLimit = 1024 * 1024 * 10;	// 업로드 용량
		String realDir = "C:\\apache-tomcat-8.5.61\\wtpwebapps\\dokey\\images(detail)";
		
		MultipartRequest multi = new MultipartRequest(req, realDir, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		
		
		int code = Integer.parseInt(multi.getParameter("code"));
		int price = Integer.parseInt(multi.getParameter("price"));
		int price2 = Integer.parseInt(multi.getParameter("price2"));
		
		String genre = multi.getParameter("genre");
		String title = multi.getParameter("title");
		String publisher = multi.getParameter("publisher");
		String trailer = multi.getParameter("trailer");
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			// 첨부파일 집합
			Enumeration<?> files = multi.getFileNames();
			
			// 다음 요소가 있으면
			while(files.hasMoreElements()) {
				// 첨부파일의 이름
				String file1 = (String) files.nextElement();
				String nullcheck = multi.getFilesystemName(file1);
				if (nullcheck == null || nullcheck.trim().equals("")) {
					list.add("-");
				} else {
					list.add(multi.getFilesystemName(file1));
				}
			} 
		} catch(Exception e) {
				e.printStackTrace();
		}

		String thumbnail1 = "dokey/images(detail)/" + list.get(4);
		String thumbnail2 = "dokey/images(detail)/" + list.get(3);
		String thumbnail3 = "dokey/images(detail)/" + list.get(2);
		String thumbnail4 = "dokey/images(detail)/" + list.get(0);
		String gamelogo = "dokey/images(detail)/" + list.get(1);
		
		String description = multi.getParameter("description");
		String developer = multi.getParameter("developer");
		String rate = multi.getParameter("rate");
		String platform = multi.getParameter("platform");
		
		String str_publishing_date = multi.getParameter("publishing_date");
		str_publishing_date = str_publishing_date.substring(2);
		str_publishing_date = str_publishing_date.replaceAll("/", "-");

		
		
//		System.out.println("--출력시험--");
//		System.out.println("정상가 " + price);
//		System.out.println("장르: "+ genre);
//		System.out.println("썸네일투" + thumbnail2);
//		System.out.println("게임로고" + gamelogo);

		
		GameVO vo = new GameVO();
		
		vo.setCode(code);
		vo.setGenre(genre);
		vo.setTitle(title);
		vo.setPublisher(publisher);
		vo.setPrice(price);
		vo.setPrice2(price2);
		vo.setTrailer(trailer);
		
		
		vo.setThumbnail1(thumbnail1);
		vo.setThumbnail2(thumbnail2);
		vo.setThumbnail3(thumbnail3);
		vo.setThumbnail4(thumbnail4);
		vo.setGamelogo(gamelogo);
		vo.setDescription(description);
		vo.setDeveloper(developer);
		vo.setRate(rate);
		vo.setPlatform(platform);
		vo.setStr_publishing_date(str_publishing_date);
		
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		int insertCnt = dao.insertGame(vo);
		
		System.out.println("인써트씨엔티값은?" + insertCnt);

		req.setAttribute("insertCnt", insertCnt);
		
		
	}


	// 게임 상세정보 조회
	@Override
	public void detail(HttpServletRequest req, HttpServletResponse res) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		DokeyDAO dao = DokeyDAOImpl.getInstance();
				
		GameVO vo = dao.getGameDetail(num);
		
		req.setAttribute("dto", vo);
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("number", number);
		
	}

	// 게임 상세정보 수정
	@Override
	public void updateGame(HttpServletRequest req, HttpServletResponse res) {
		
		int code = Integer.parseInt(req.getParameter("code"));
		
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		GameVO vo = dao.updateGame(code);
		
		req.setAttribute("dto", vo);
		
	}

	
	// 게임 상세정보 수정 처리 
	@Override
	public void updateGameAction(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		int sizeLimit = 1024 * 1024 * 10;	// 업로드 용량
		String realDir = "C:\\apache-tomcat-8.5.61\\wtpwebapps\\dokey\\images(detail)";
		
		MultipartRequest multi = new MultipartRequest(req, realDir, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		
		
		int code = Integer.parseInt(multi.getParameter("code"));
		int price = Integer.parseInt(multi.getParameter("price"));
		int price2 = Integer.parseInt(multi.getParameter("price2"));
		
		String genre = multi.getParameter("genre");
		String title = multi.getParameter("title");
		String publisher = multi.getParameter("publisher");
		String trailer = multi.getParameter("trailer");
		
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			// 첨부파일 집합
			Enumeration<?> files = multi.getFileNames();
			
			// 다음 요소가 있으면
			while(files.hasMoreElements()) {
				// 첨부파일의 이름
				String file1 = (String) files.nextElement();
				String nullcheck = multi.getFilesystemName(file1);
				if (nullcheck == null || nullcheck.trim().equals("")) {
					list.add("-");
				} else {
					list.add(multi.getFilesystemName(file1));
				}
			} 
		} catch(Exception e) {
				e.printStackTrace();
		}

		String thumbnail1 = "dokey/images(detail)/" + list.get(4);
		String thumbnail2 = "dokey/images(detail)/" + list.get(3);
		String thumbnail3 = "dokey/images(detail)/" + list.get(2);
		String thumbnail4 = "dokey/images(detail)/" + list.get(0);
		String gamelogo = "dokey/images(detail)/" + list.get(1);
		int checkList = 1;
		
		for (String str : list) {
			if (str == "-") {
				checkList = 0;
			}
		}
		
		System.out.println(checkList);
		
		System.out.println("썸네일1(대표사진)" + list.get(0));
		System.out.println(list.get(1));
		System.out.println(list.get(2));
		System.out.println(list.get(3));
		System.out.println("로고" + list.get(4));
		System.out.println("리스트길이[size]:" + list.size());
		
		String description = multi.getParameter("description");
		String developer = multi.getParameter("developer");
		String rate = multi.getParameter("rate");
		String platform = multi.getParameter("platform");
		
		String str_publishing_date = multi.getParameter("publishing_date");
		str_publishing_date = str_publishing_date.substring(2);
		str_publishing_date = str_publishing_date.replaceAll("/", "-");

// 		타임스탬프 형태로 DB에 데이터 삽입하려다가 실패..
//		String str_publishing_date = "2021-01-03";
//		Date publishing_date = Date.valueOf(str_publishing_date);
//		System.out.println(publishing_date);
//		System.out.println(publishing_date.getClass().getName());
		
		GameVO vo = new GameVO();
		
		vo.setCode(code);
		vo.setGenre(genre);
		vo.setTitle(title);
		vo.setPublisher(publisher);
		vo.setPrice(price);
		vo.setPrice2(price2);
		vo.setTrailer(trailer);
		
		vo.setThumbnail1(thumbnail1);
		vo.setThumbnail2(thumbnail2);
		vo.setThumbnail3(thumbnail3);
		vo.setThumbnail4(thumbnail4);
		vo.setGamelogo(gamelogo);
		vo.setDescription(description);
		vo.setDeveloper(developer);
		vo.setRate(rate);
		vo.setPlatform(platform);
		vo.setStr_publishing_date(str_publishing_date);
//		vo.setPublishing_date(publishing_date);
		
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		int updateCnt = dao.updateGameAction(vo, checkList);
		
		System.out.println("업데잍씨엔티값은?" + updateCnt);

		req.setAttribute("updateCnt", updateCnt);
		
	}

	// 게임 통계 조회
	@Override
	public void getStatistics(HttpServletRequest req, HttpServletResponse res) {
		
		int code = (int) req.getAttribute("code");
		System.out.println("코드 1137 출력" + code);
		
		ManagerDAO dao = ManagerDAOImpl.getInstance();
		
		ArrayList list = dao.getStatistics(code);
		
		req.setAttribute("list", list);
		
	}
	
}
