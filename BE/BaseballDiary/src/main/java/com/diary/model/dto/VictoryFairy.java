package com.diary.model.dto;

public class VictoryFairy {
	private String userId;
	private String nickname;
	private String year;
	private int totalCount;
	private int winningCount;
	private double percent;
	
	public VictoryFairy() {
	}
	
	




	public VictoryFairy(String userId, String nickname, String year, int totalCount, int winningCount, double percent) {
		super();
		this.userId = userId;
		this.nickname = nickname;
		this.year = year;
		this.totalCount = totalCount;
		this.winningCount = winningCount;
		this.percent = percent;
	}






	public String getNickname() {
		return nickname;
	}






	public void setNickname(String nickname) {
		this.nickname = nickname;
	}






	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getWinningCount() {
		return winningCount;
	}

	public void setWinningCount(int winningCount) {
		this.winningCount = winningCount;
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}

	@Override
	public String toString() {
		return "VictoryFairy [userId=" + userId + ", year=" + year + ", totalCount=" + totalCount + ", winningCount="
				+ winningCount + ", percent=" + percent + "]";
	}
	
	
}
