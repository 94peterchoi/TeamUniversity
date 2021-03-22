package dokey_vo;

import java.sql.Date;
import java.sql.Timestamp;

public class GameVO {
	private int num;
	private int code;
	private String genre;
	private String title;
	private String publisher;
	private int price;
	private int price2;
	private int discounted;
	
	private String trailer;
	private String thumbnail1;
	private String thumbnail2;
	private String thumbnail3;
	private String thumbnail4;
	private String gamelogo;
	
	private String description;
	private String developer;
	private String rate;
	private String platform;
	private Date publishing_date;
	private String str_publishing_date;
	
	private Timestamp posting_date;
	private int game_review;
	private int game_sell;
	private int game_view;
	
	
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice2() {
		return price2;
	}
	public void setPrice2(int price2) {
		this.price2 = price2;
	}
	public int getDiscounted() {
		return discounted;
	}
	public void setDiscounted(int discounted) {
		this.discounted = discounted;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getThumbnail1() {
		return thumbnail1;
	}
	public void setThumbnail1(String thumbnail1) {
		this.thumbnail1 = thumbnail1;
	}
	public String getThumbnail2() {
		return thumbnail2;
	}
	public void setThumbnail2(String thumbnail2) {
		this.thumbnail2 = thumbnail2;
	}
	public String getThumbnail3() {
		return thumbnail3;
	}
	public void setThumbnail3(String thumbnail3) {
		this.thumbnail3 = thumbnail3;
	}
	public String getThumbnail4() {
		return thumbnail4;
	}
	public void setThumbnail4(String thumbnail4) {
		this.thumbnail4 = thumbnail4;
	}
	public String getGamelogo() {
		return gamelogo;
	}
	public void setGamelogo(String gamelogo) {
		this.gamelogo = gamelogo;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	
	
	public Date getPublishing_date() {
		return publishing_date;
	}
	
	public void setPublishing_date(Date publishing_date) {
		this.publishing_date = publishing_date;
	}
	
	public String getStr_publishing_date() {
		return str_publishing_date;
	}

	public void setStr_publishing_date(String str_publishing_date) {
		this.str_publishing_date = str_publishing_date;
	}

	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Timestamp getPosting_date() {
		return posting_date;
	}
	public void setPosting_date(Timestamp posting_date) {
		this.posting_date = posting_date;
	}
	public int getGame_review() {
		return game_review;
	}
	public void setGame_review(int game_review) {
		this.game_review = game_review;
	}
	public int getGame_sell() {
		return game_sell;
	}
	public void setGame_sell(int game_sell) {
		this.game_sell = game_sell;
	}
	public int getGame_view() {
		return game_view;
	}
	public void setGame_view(int game_view) {
		this.game_view = game_view;
	}
	
}

