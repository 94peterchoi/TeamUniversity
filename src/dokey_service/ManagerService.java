package dokey_service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ManagerService {
	
	// 관리자 서비스 클래스
	
	// 스토어 목록 조회
	public void readAll(HttpServletRequest req, HttpServletResponse res);
	
	// 스토어 목록에서 게임 삭제
	public void deleteGame(HttpServletRequest req, HttpServletResponse res);
	
	// 스토어 목록에 게임 추가
	public void insertGame(HttpServletRequest req, HttpServletResponse res) throws IOException;
	
	// 게임 상세정보 조회
	public void detail(HttpServletRequest req, HttpServletResponse res);

	// 게임 상세정보 수정
	public void updateGame(HttpServletRequest req, HttpServletResponse res);
	
	// 게임 상세정보 수정 처리 
	public void updateGameAction(HttpServletRequest req, HttpServletResponse res) throws IOException;
	
	// 게임 통계 조회
	public void getStatistics(HttpServletRequest req, HttpServletResponse res);
	
	
}	
