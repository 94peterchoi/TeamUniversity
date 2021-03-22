package dokey_controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dokey_service.ManagerService;
import dokey_service.ManagerServiceImpl;

@WebServlet("*.m")
public class ManagerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ManagerController() {
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
			ManagerService service = new ManagerServiceImpl();
			
			// 일단 베껴옴, 처음 if문  : 스토어 (전체보기)
			if(url.equals("/store.m") || url.equals("/*.m")) {
				System.out.println("관리자 컨트롤러");
				System.out.println("[url ==> store.m]");
				
				service.readAll(req, res);

				viewPage = "/manager/all.jsp";
			
			// 관리자 로그아웃	
			} else if(url.equals("/logout.m")) {
					System.out.println("[url ==> /logout.m]");
					
					req.getSession().invalidate(); // 모든 세션 삭제
					
					viewPage = "/dokey/home.jsp"; // 다시 일반페이지로 넘기기			
							
			// 스토어 (장르별) - 위에 전체보기랑 똑같은 로직
			} else if (url.equals("/genre.m")) {
				System.out.println("관리자 컨트롤러");
				System.out.println("[url ==> genre.m]");
				
				service.readAll(req, res);
				
				viewPage = "/manager/all.jsp";
				
			// 판매 중인 게임 삭제하기
			} else if (url.equals("/deleteAction.m")) {
				System.out.println("관리자 컨트롤러");
				System.out.println("[url ==> deleteAction.m]");
				
//				String strNum = req.getParameter("numValue");
//				String strCode = req.getParameter("codeValue");
//				
//				System.out.println("all.jsp에서 받아온 {dto.num}값 :" + strNum);
//				System.out.println("all.jsp에서 받아온 {dto.code}값 :" + strCode);
				
				service.deleteGame(req, res);
				
				viewPage = "/manager/home.jsp";
				
				
			// 스토어에 게임 올리기 (게시글 쓰기)
			} else if(url.equals("/insertGame.m")) {
				System.out.println("관리자 컨트롤러");
				System.out.println("[url ==> /insertGame.m]");
				
				viewPage = "/manager/insertGame.jsp";
				
			// 스토어에 게임 올리기 처리하기
			} else if(url.equals("/insertGameAction.m")) {
				System.out.println("관리자 컨트롤러");
				System.out.println("[url ==> /insertGameAction.m]");
	
				service.insertGame(req, res);
				
				viewPage = "/manager/home.jsp";
				
				
			// 게임 상세정보 조회
			} else if (url.equals("/detail.m")) {
				System.out.println("[url ==> /detail.m]");
				
				service.detail(req, res);
				
				viewPage = "/manager/detail.jsp";
				
			// 게임 수정하기
			} else if (url.equals("/updateGame.m")) {
				System.out.println("[url ==> /updateGame.m]");
				
				service.updateGame(req, res);
				
				viewPage = "/manager/updateGame.jsp";
				
			
			// 게임 수정하기 처리
			} else if (url.equals("/updateGameAction.m")) {
				System.out.println("[url ==> /updateGameAction.m]");
				
				service.updateGameAction(req, res);
				service.readAll(req, res);
				
				viewPage = "/manager/all.jsp";
			
			// 게임 통계 조회
			} else if (url.equals("/statistics.m")) {
				System.out.println("[url ==> /statistics.m]");
				
				req.setAttribute("code", 1137);
				
				service.getStatistics(req, res);
				
				viewPage = "/testChart/proto2.jsp";
			}
			
			
			RequestDispatcher dispatcher = req.getRequestDispatcher(viewPage);
			dispatcher.forward(req, res);

	}

}



