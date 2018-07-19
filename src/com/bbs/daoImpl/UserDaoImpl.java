package com.bbs.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bbs.dao.UserDao;
import com.bbs.po.User;
import com.bbs.util.DbUtil;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
	

	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public UserDaoImpl(Connection connection) {
		super(connection);
		this.connection = connection;
	}
	//用户登录，若无账号则注册
	@Override
	public User userLogin(User u) {
		User user = null;
		try {
			if(connection==null)
				System.out.println("null");
			preparedStatement = connection.prepareStatement("select * from user where userName=? and passWord=?");
			preparedStatement.setString(1, u.getUserName());
			preparedStatement.setString(2, u.getPassWord());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong(1));
				user.setUserName(resultSet.getString(2));
				user.setPassWord(resultSet.getString(3));
				user.setNickName(resultSet.getString(4));
				user.setGender(resultSet.getString(5));
				user.setRoleId(resultSet.getInt(6));
				user.setHeadImg(resultSet.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.getInstance().closeConnection(connection);
		}
		return user;
	}
	
	//根据用户名查找用户信息
	@Override
	public User getByName(String name) {
		User user = null;
		try {
			preparedStatement = connection.prepareStatement("select * from user where userName=?");
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getLong(1));
				user.setUserName(resultSet.getString(2));
				user.setPassWord(resultSet.getString(3));
				user.setNickName(resultSet.getString(4));
				user.setGender(resultSet.getString(5));
				user.setRoleId(resultSet.getInt(6));
				user.setHeadImg(resultSet.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DbUtil.getInstance().closeConnection(connection);
		}
		return user;
	}
}
