package com.diary.model.dto;

public class Diary {
	private String userId;
	private int diaryId;
	private String title;
	private String content;
	private String img;
	private String gamedate;
	private String gametime;
	private int isStadium;
	private String location;
	private int homeTeam;
	private int homeScore;
	private int awayTeam;
	private int awayScore;
	private int result;
	private String homeTeamName;
	private String awayTeamName;
	private String homeTeamImg;
	private String awayTeamImg;
	
	public Diary() {
	}

	public Diary(String userId, int diaryId, String title, String gamedate) {
		super();
		this.userId = userId;
		this.diaryId = diaryId;
		this.title = title;
		this.gamedate = gamedate;
	}
	
	
	
	
	
	public Diary(String title, String img, String gamedate) {
		super();
		this.title = title;
		this.img = img;
		this.gamedate = gamedate;
	}
	
	

	public Diary(String userId, int diaryId, String title, String content, String img, String gamedate, String gametime,
			int isStadium, String location, int homeTeam, int homeScore, int awayTeam, int awayScore, int result) {
		super();
		this.userId = userId;
		this.diaryId = diaryId;
		this.title = title;
		this.content = content;
		this.img = img;
		this.gamedate = gamedate;
		this.gametime = gametime;
		this.isStadium = isStadium;
		this.location = location;
		this.homeTeam = homeTeam;
		this.homeScore = homeScore;
		this.awayTeam = awayTeam;
		this.awayScore = awayScore;
		this.result = result;
	}
	
	

	public Diary(String userId, int diaryId, String title, String content, String img, String gamedate, String gametime,
			int isStadium, String location, int homeTeam, int homeScore, int awayTeam, int awayScore, int result,
			String homeTeamName, String awayTeamName, String homeTeamImg, String awayTeamImg) {
		super();
		this.userId = userId;
		this.diaryId = diaryId;
		this.title = title;
		this.content = content;
		this.img = img;
		this.gamedate = gamedate;
		this.gametime = gametime;
		this.isStadium = isStadium;
		this.location = location;
		this.homeTeam = homeTeam;
		this.homeScore = homeScore;
		this.awayTeam = awayTeam;
		this.awayScore = awayScore;
		this.result = result;
		this.homeTeamName = homeTeamName;
		this.awayTeamName = awayTeamName;
		this.homeTeamImg = homeTeamImg;
		this.awayTeamImg = awayTeamImg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(int diaryId) {
		this.diaryId = diaryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getGamedate() {
		return gamedate;
	}

	public void setGamedate(String gamedate) {
		this.gamedate = gamedate;
	}

	public String getGametime() {
		return gametime;
	}

	public void setGametime(String gametime) {
		this.gametime = gametime;
	}


	public int getIsStadium() {
		return isStadium;
	}

	public void setIsStadium(int isStadium) {
		this.isStadium = isStadium;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(int homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public int getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(int awayTeam) {
		this.awayTeam = awayTeam;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getHomeTeamName() {
		return homeTeamName;
	}

	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	public String getAwayTeamName() {
		return awayTeamName;
	}

	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	public String getHomeTeamImg() {
		return homeTeamImg;
	}

	public void setHomeTeamImg(String homeTeamImg) {
		this.homeTeamImg = homeTeamImg;
	}

	public String getAwayTeamImg() {
		return awayTeamImg;
	}

	public void setAwayTeamImg(String awayTeamImg) {
		this.awayTeamImg = awayTeamImg;
	}

	@Override
	public String toString() {
		return "Diary [userId=" + userId + ", diaryId=" + diaryId + ", title=" + title + ", content=" + content
				+ ", img=" + img + ", gamedate=" + gamedate + ", gametime=" + gametime + ", isStadium=" + isStadium
				+ ", location=" + location + ", homeTeam=" + homeTeam + ", homeScore=" + homeScore + ", awayTeam="
				+ awayTeam + ", awayScore=" + awayScore + ", result=" + result + ", homeTeamName=" + homeTeamName
				+ ", awayTeamName=" + awayTeamName + ", homeTeamImg=" + homeTeamImg + ", awayTeamImg=" + awayTeamImg
				+ "]";
	}

	

}
