package dokey_vo;

import java.sql.Timestamp;

public class UserinfoVO {
		
	private String id;	// 식별자
	private String pwd;
	private String nickname;
	private String email;
	private String hp;
	private String profile_img;	// 프로필사진
	private int point;			// 충전금(포인트)
	private int count_review;	// 작성 리뷰 수
	private int count_game;		// 보유한 게임 수
	private Timestamp reg_date;	// 가입일자
	private Timestamp last_date;	// 마지막 접속일자
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getHp() {
		return hp;
	}
	
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public String getProfile_img() {
		return profile_img;
	}
	
	public void setProfile_img(String profile_img) {
		this.profile_img = profile_img;
	}
	
	public int getCount_review() {
		return count_review;
	}
	
	public void setCount_review(int count_review) {
		this.count_review = count_review;
	}
	
	public int getCount_game() {
		return count_game;
	}
	
	public void setCount_game(int count_game) {
		this.count_game = count_game;
	}
	
	public Timestamp getReg_date() {
		return reg_date;
	}
	
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	public Timestamp getLast_date() {
		return last_date;
	}
	
	public void setLast_date(Timestamp last_date) {
		this.last_date = last_date;
	}
	
	public int getPoint( ) {
		return point;
	}
	
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
