package dokey_service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface DokeyService {
	
	// 회원가입 처리
	public void signinAction(HttpServletRequest req, HttpServletResponse res);
	
	// 회원가입 아이디 중복확인
	public void confirmId(HttpServletRequest req, HttpServletResponse res);
	
	// 로그인 처리
	public void loginAction(HttpServletRequest req, HttpServletResponse res);
	
	// 스토어 목록 전체 조회
	public void readAll(HttpServletRequest req, HttpServletResponse res);
	
	// 게임 상세정보 조회
	public void detail(HttpServletRequest req, HttpServletResponse res);
	
	// 특정 게임 전체리뷰 조회
	public void seeReview(HttpServletRequest req, HttpServletResponse res);

	// 고객 포인트 조회 (게임구매 화면)
	public void checkPoint(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 게임구매 (포인트로 게임 사기)
	public void buyGame(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 구매이력 조회
	public void seeBuylist(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 게임환불
	public void refundGame(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 장바구니 담기	버튼 클릭
	public void wishlist(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 장바구니 보기
	public void viewWishlist(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 장바구니에서 상품 삭제
	public void deleteWishlist(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 본인 프로필 조회
	public void profile(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 본인 포인트 충전내역 조회 (프로필에서 조회가능)
	public void viewPoint(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 포인트 충전하기
	public void addPoint(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 본인 프로필 수정
	public void updateProfile(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 본인 프로필 수정 - 상세페이지
	public void updateProfileAction(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 본인 프로필 수정 처리
	public void updateProfileFinish(HttpServletRequest req, HttpServletResponse res);
	
	// 고객 회원탈퇴 - 회원탈퇴 처리 및 탈퇴기록 관리자 DB에 저장
	public void deleteProfile(HttpServletRequest req, HttpServletResponse res);
	
	
}
