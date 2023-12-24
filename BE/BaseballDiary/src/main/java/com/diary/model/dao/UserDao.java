package com.diary.model.dao;

import java.util.List;

import com.diary.model.dto.User;

public interface UserDao {
	
	//유저 정보 가져오기
	User selectUser(String id);
	
	//유저 정보 등록(회원가입)
	int insertUser(User user);
	
	//유저 정보 수정
	int updateUser(User user);
	
	//유저 정보 삭제(탈퇴)
	int deleteUser(String id);
	
	//유저 프로필 (사진, 상메, 팀) 가져오기
	User selectProfile(String id);
	
	//유저 리스트 가져오기
	List<User> selectUserList();
}
