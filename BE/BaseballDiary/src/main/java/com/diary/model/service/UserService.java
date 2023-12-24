package com.diary.model.service;

import java.util.List;

import com.diary.model.dto.User;

public interface UserService {
	
	//유저 정보 가져오기
	User getUser(String id);
	
	//유저 정보 등록(회원가입)
	int signUp(User user);
	
	//유저 정보 수정
	int modifyUser(User user);
	
	//유저 정보 삭제(탈퇴)
	int removeUser(String id);
	
	//유저 프로필 (사진, 상메, 팀) 가져오기
	User getProfile(String id);
	
	//유저 리스트 가져오기
	List<User> getUserList();
	
}
