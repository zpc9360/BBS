package com.bbs.dao;

import com.bbs.po.User;

public interface UserDao extends BaseDao {

	//用户登陆
	public User userLogin(User user);
	//用户名查找用户
	public User getByName(String name);

}
