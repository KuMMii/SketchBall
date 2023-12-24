package com.diary.model.dto;

public class User {
	private String id;
	private String pw;
	private String name;
	private String nickname;
	private int team;
	private String statusMessage;
	private String profileImg;
	private String teamName;
	
	public User() {
	}
	
	public User(String id, String pw, String name, String nickname, int team, String statusMessage, String profileImg) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.team = team;
		this.statusMessage = statusMessage;
		this.profileImg = profileImg;
	}

	

	public User(String id, String pw, String name, String nickname, int team, String statusMessage, String profileImg,
			String teamName) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.team = team;
		this.statusMessage = statusMessage;
		this.profileImg = profileImg;
		this.teamName = teamName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	
	

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", nickname=" + nickname + ", team=" + team
				+ ", statusMessage=" + statusMessage + ", profileImg=" + profileImg + "]";
	}
	
	
	
	
}
