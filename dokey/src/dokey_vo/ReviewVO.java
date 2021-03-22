package dokey_vo;

import java.sql.Timestamp;

public class ReviewVO {
	private String userid;
	private String nickname;
	private int gamecode;
	private Timestamp posting_date;
	private int starpoint;
	private String goodbad;
	private String content;
	private int count_good;
	private int count_game;
	
	private String profile;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getGamecode() {
		return gamecode;
	}
	
	public void setGamecode(int gamecode) {
		this.gamecode = gamecode;
	}
	
	public Timestamp getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(Timestamp posting_date) {
		this.posting_date = posting_date;
	}
	public int getStarpoint() {
		return starpoint;
	}
	public void setStarpoint(int starpoint) {
		this.starpoint = starpoint;
	}
	public String getGoodbad() {
		return goodbad;
	}
	public void setGoodbad(String goodbad) {
		this.goodbad = goodbad;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getCount_good() {
		return count_good;
	}
	public void setCount_good(int count_good) {
		this.count_good = count_good;
	}
	
	public int getCount_game() {
		return count_game;
	}
	
	public void setCount_game(int count_game) {
		this.count_game = count_game;
	}
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

	
}

