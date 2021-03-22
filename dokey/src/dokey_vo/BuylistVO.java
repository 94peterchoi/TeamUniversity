package dokey_vo;

import java.sql.Timestamp;

public class BuylistVO {	// 고객 구매이력 조회 바구니

	private String title;		// 게임제목
	private int gamecode;		// 게임코드
	private int price;			// 결제가격
	private Timestamp pay_day;	// 결제날짜
	private int interval;		// 환불가능시간 계산용
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getGamecode() {
		return gamecode;
	}
	
	public void setGamecode(int gamecode) {
		this.gamecode = gamecode;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public Timestamp getPay_day() {
		return pay_day;
	}
	
	public void setPay_day(Timestamp pay_day) {
		this.pay_day = pay_day;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	
}
