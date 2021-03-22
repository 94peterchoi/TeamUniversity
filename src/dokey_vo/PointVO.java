package dokey_vo;

import java.sql.Timestamp;

public class PointVO {	// 고객 포인트 충전내역 조회 바구니 (프로필에서 조회 가능)
	
	private String userid;		// 유저아이디
	private Timestamp pay_day;	// 충전시점
	private int point;			// 해당시점 포인트 충전금액
	private String payment;		// 결제수단(무통장입금, 계좌이체 등등..)
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Timestamp getPay_day() {
		return pay_day;
	}
	public void setPay_day(Timestamp pay_day) {
		this.pay_day = pay_day;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
}


