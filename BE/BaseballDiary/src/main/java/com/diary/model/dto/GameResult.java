package com.diary.model.dto;

public class GameResult {
	private int homeTeam;
	private int homeScore;
	private int awayTeam;
	private int awayScore;
	private String homeImage;
	private String awayImage;
	
	public GameResult() {
	}

	public GameResult(int homeTeam, int homeScore, int awayTeam, int awayScore, String homeImage, String awayImage) {
		super();
		this.homeTeam = homeTeam;
		this.homeScore = homeScore;
		this.awayTeam = awayTeam;
		this.awayScore = awayScore;
		this.homeImage = homeImage;
		this.awayImage = awayImage;
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

	public String getHomeImage() {
		return homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

	public String getAwayImage() {
		return awayImage;
	}

	public void setAwayImage(String awayImage) {
		this.awayImage = awayImage;
	}

	@Override
	public String toString() {
		return "GameResult [homeTeam=" + homeTeam + ", homeScore=" + homeScore + ", awayTeam=" + awayTeam
				+ ", awayScore=" + awayScore + ", homeImage=" + homeImage + ", awayImage=" + awayImage + "]";
	}
		
}
