package dokey_vo;

import java.sql.Timestamp;

public class WishlistVO {
	
	private String url;			// 해당게임 상세보기 url
	private String logo;		// 게임로고사진
	private String title;		// 게임제목
	private int price;			// 현재가격
	private Timestamp pickdate;	// 찜한날짜
	private int gamecode;		// 게임코드	
	
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getLogo() {
		return logo;
	}
	
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Timestamp getPickdate() {
		return pickdate;
	}
	
	public void setPickdate(Timestamp pickdate) {
		this.pickdate = pickdate;
	}

	public int getGamecode() {
		return gamecode;
	}

	public void setGamecode(int gamecode) {
		this.gamecode = gamecode;
	}
	
	
	
}
