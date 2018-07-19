package com.bbs.dao;

import java.util.List;

import com.bbs.bo.UserTopic;

public interface UserTopicDao {
	
	public List<UserTopic> getAll(long topicId);
	
}
