package dokey_persistence;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dokey_vo.BuylistVO;
import dokey_vo.GameVO;
import dokey_vo.PointVO;
import dokey_vo.ReviewVO;
import dokey_vo.UserinfoVO;
import dokey_vo.WishlistVO;

public interface DokeyDAO {
	
	
	// 회원가입 아이디 중복확인
	public int idCheck(String id);
	
	// 회원가입 처리
	public int insertUser(UserinfoVO vo);
	
	// 로그인 처리
	public int idPwdCheck(String strId, String strPwd);
	
	// 스토어 목록 게임 개수 구하기
	public int getGenreCount(String genre);
	
	// 스토어 목록 가져오기 
	public ArrayList<GameVO> getGenreList(String genre, int start, int end, int order);
	
	// 스토어 목록 가져오기 (기준별 정렬 value -> 1: 출시일빠른 2: 출시일늦은 3: 높은가격순 4: 낮은가격순 ...) 
//	public ArrayList<GameVO> getGenreList(String genre, int start, int end, int value);
	// 이미 getGenreList메서드에서 구현된듯
	
	// 게임 상세정보 조회 데이터 가져오기
	public GameVO getGameDetail (int num);
	
	// 조회수 증가 메서드 
	public void increaseView(int num);
	
	// 특정 게임 전체 리뷰 조회하기
	public ArrayList<ReviewVO> seeReview (int code);
	
	// 고객 포인트 조회 (게임구매 화면)
	public int checkPoint(String userid);
	
	// 고객 포인트 충전
	public int addPoint(String userid, int point);
	
	// 고객 게임구매 (포인트로 게임 사기) 
	public int buyGame(int code, String userid);
	
	// 고객이 구매한 게임 지금구매, 장바구니구매 버튼 막기
	public int block(int num, String userid);

	// 고객 구매이력 조회
	public ArrayList<BuylistVO> seeBuylist(String userId);
	
	// 고객 게임환불
	public int refundGame(String userid, int code, int price);
	
	// 고객 장바구니 담기
	public int wishlist(String userid, int code, String url);
	
	// 고객 이미 장바구니에 있는 상품입니다
	public int picked(int code, String userid);
	
	// 고객 장바구니 보기
	public ArrayList<WishlistVO> viewWishlist(String userid);
	
	// 고객 장바구니에서 상품 삭제
	public int deleteWishlist(String userid, int code);
	
	// 고객 본인 프로필 조회
	public UserinfoVO profile(String userid);
	
	// 고객 포인트 충전내역(게시판) 개수 구하기 
	public int getPointCount(String userid);
	
	// 고객 포인트 충전내역 조회 (프로필에서 조회가능)
	public ArrayList<PointVO> viewPoint(String userid, int start, int end);
	
	// 고객 본인 프로필 수정 - 회원정보 가져오기 
	public UserinfoVO getUserInfo(String userid);
	
	// 고객 본인 프로필 수정 - DB 업데이트
	public int updateUserProfile(UserinfoVO vo);
	
	// 고객 회원탈퇴 - 회원탈퇴 처리 및 탈퇴기록 관리자 DB에 저장
	public int deleteProfile(String userid, String reason, String suggestion);
	
	
	
}
