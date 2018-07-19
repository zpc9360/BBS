package com.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbs.bo.UserTopic;
import com.bbs.dao.UserTopicDao;

public class UserTopicDaoImpl implements UserTopicDao {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	
	public UserTopicDaoImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<UserTopic> getAll(long topicId) {
		List<UserTopic> list = new ArrayList<UserTopic>();
		String sql = "select * from user,topic where user.id=topic.userId and topic.topicId=" + topicId;
//		System.out.println(sql);
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()) {
				UserTopic ut = new UserTopic();
				ut.setUserId(resultSet.getLong("userId"));
				ut.setNickName(resultSet.getString("nickName"));
				ut.setContent(resultSet.getString("content"));
				ut.setCreateDate(resultSet.getString("createDate"));
				ut.setRole(getRole(resultSet.getInt("roleId")));
				ut.setHeadImg(resultSet.getString("headImg"));
				list.add(ut);
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	private String getRole(int roleId) {
		switch (roleId) {
		case 2:
			return "用户";
		case 3:
			return "Vip用户";
		case 4:
			return "版块管理员";
		case 5:
			return "区域管理员";
		case 6:
			return "论坛管理员";
		default:
			return "用户";
		}
	}

}
