package com.bbs.service;

import java.sql.Connection;

import com.bbs.dao.UserDao;
import com.bbs.daoImpl.UserDaoImpl;
import com.bbs.po.User;
import com.bbs.util.DbUtil;

public class UserService {
	
	private UserDao userDao;
	private Connection connection;

	public UserService() {
		super();
		connection = DbUtil.getInstance().getConnetion();
		userDao = new UserDaoImpl(connection);
	}
	
	//登陆
	public User userLogin(User user) {
		return userDao.userLogin(user);
	}
	//注册
	public boolean userRegist(User user) {
		return userDao.add(user);
	}

	
}
