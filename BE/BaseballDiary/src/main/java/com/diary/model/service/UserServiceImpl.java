package com.diary.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diary.model.dao.UserDao;
import com.diary.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao uDao;

	@Override
	public User getUser(String id) {
		return uDao.selectUser(id);
	}

	@Override
	public int signUp(User user) {
		return uDao.insertUser(user);
	}

	@Override
	public int modifyUser(User user) {
		return uDao.updateUser(user);
	}

	@Override
	public int removeUser(String id) {
		return uDao.deleteUser(id);
	}

	@Override
	public User getProfile(String id) {
		return uDao.selectProfile(id);
	}

	@Override
	public List<User> getUserList() {
		return uDao.selectUserList();
	}

}
