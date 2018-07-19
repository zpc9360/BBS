package com.bbs.service;

import java.util.List;

import com.bbs.bo.UserTopic;
import com.bbs.dao.UserTopicDao;
import com.bbs.daoImpl.UserTopicDaoImpl;
import com.bbs.util.DbUtil;

public class UserToipcService {
	
	private UserTopicDao userTopicDao;

	public UserToipcService() {
		super();
		userTopicDao = new UserTopicDaoImpl(DbUtil.getInstance().getConnetion());
	}

	public List<UserTopic> getAll(long topicId){
		return userTopicDao.getAll(topicId);
	}
	
}
