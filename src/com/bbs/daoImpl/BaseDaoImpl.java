package com.bbs.daoImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbs.dao.BaseDao;
import com.bbs.util.DbUtil;

public class BaseDaoImpl implements BaseDao {

	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	public BaseDaoImpl(Connection connection) {
		this.connection = connection;
		this.statement = null;
		this.preparedStatement = null;
		this.resultSet = null;
	}
	
	@Override
	public boolean add(Object obj) {

		boolean flag = false;
		Class<?> c = (Class<?>) obj.getClass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer sql = new StringBuffer("insert into " + c.getSimpleName() + " values(null,");
		for (int i = 2; i < fields.length; i++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			for (int i = 2; i < fields.length; i++) {
				fields[i].setAccessible(true);
				preparedStatement.setObject(i - 1, fields[i].get(obj));
			}
			flag = preparedStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}

		return flag;
	}

	@Override
	public boolean isExist(Class<?> c, String str) {
		boolean flag = false;
		String sql = "select * from " + c.getSimpleName() + " where " + str;
		try {
			resultSet = connection.createStatement().executeQuery(sql);
			flag = resultSet.next() ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}

		return flag;
	}

	@Override
	public boolean deleteById(Class<?> c, long id) {

		boolean flag = false;
		String sql = "delete from " + c.getSimpleName() + " where id = " + id;
		try {
			statement = connection.createStatement();
			flag = statement.executeUpdate(sql) > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}

		return flag;
	}

	@Override
	public List<Object> quaryAllCustom(Class<?> c, String custom) {
		String sql = "select * from " + c.getSimpleName() + " " + custom;
		// System.out.println(sql);
		return quaryAllBody(c, sql);
	}

	@Override
	public List<Object> quaryAll(Class<?> c, String order) {
		String sql = "select * from " + c.getSimpleName() + " order by " + " " + order;
		return quaryAllBody(c, sql);
	}

	@Override
	public List<Object> quaryAll(Class<?> c) {
		String sql = "select * from " + c.getSimpleName();
		return quaryAllBody(c, sql);
	}

	private List<Object> quaryAllBody(Class<?> c, String sql) {
		List<Object> list = new ArrayList<>();
		// System.out.println(sql);
		Field[] fields = c.getDeclaredFields();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Constructor<?> constructor = c.getConstructor();
				Object obj = constructor.newInstance();
				for (int i = 1; i < fields.length; i++) {
					fields[i].setAccessible(true);
					fields[i].set(obj, resultSet.getObject(fields[i].getName()));
				}
				list.add(obj);
			}
		} catch (SQLException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}

		return list;

	}

	@Override
	public Object quaryById(Class<?> c, long id) {
		Object obj = null;
		String sql = "select * from " + c.getSimpleName() + " where id = " + id;
		Field[] fields = c.getDeclaredFields();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Constructor<?> constructor = c.getConstructor();
				obj = constructor.newInstance();
				for (int i = 1; i < fields.length; i++) {
					fields[i].setAccessible(true);
					fields[i].set(obj, resultSet.getObject(fields[i].getName()));
				}
			}
		} catch (SQLException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}
		return obj;
	}

	@Override
	public boolean update(Object obj) {
		boolean flag = false;
		Class<?> c = obj.getClass();
		StringBuffer sql = new StringBuffer("update " + c.getSimpleName() + " set ");
		Field[] fields = c.getDeclaredFields();
		for (int i = 2; i < fields.length; i++) {
			sql.append(fields[i].getName() + "=?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where " + fields[1].getName() + "=?");
		// System.out.println(sql);

		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			for (int i = 2; i < fields.length; i++) {
				fields[i].setAccessible(true);
				preparedStatement.setObject(i - 1, fields[i].get(obj));
				// System.out.println(i - 1 + ":" + fields[i].get(obj));
			}
			fields[1].setAccessible(true);
			// System.out.println(fields.length - 1 + ":" + fields[1].get(obj));
			preparedStatement.setObject((fields.length - 1), fields[1].get(obj));
			flag = preparedStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			DbUtil.getInstance().closeConnection(connection);
		}
		return flag;
	}

	void closeStream() {
		try {
			if (preparedStatement != null && !preparedStatement.isClosed()) {
				preparedStatement.close();
			}
			if (statement != null && !statement.isClosed()) {
				statement.close();
			}
			if (resultSet != null && !resultSet.isClosed()) {
				resultSet.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, String value) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "='" + value + "'";
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, long value) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, int value) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, String value, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "='" + value + "'" + " " + order;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, long value, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " " + order;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByParm(Class<?> c, String key, int value, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " " + order;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, String value, int pageStart, int pageNum, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "='" + value + "'" + " " + order
				+ " limit " + pageStart + "," + pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, long value, int pageStart, int pageNum, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " " + order + " limit "
				+ pageStart + "," + pageNum;
		// System.out.println(sql);
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, int value, int pageStart, int pageNum, String order) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " " + order + " limit "
				+ pageStart + "," + pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, String value, int pageStart, int pageNum) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "='" + value + "' limit " + pageStart
				+ "," + pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, long value, int pageStart, int pageNum) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " limit " + pageStart + ","
				+ pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String key, int value, int pageStart, int pageNum) {
		String sql = "select * from " + c.getSimpleName() + " where " + key + "=" + value + " limit " + pageStart + ","
				+ pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, int pageStart, int pageNum, String order) {
		String sql = "select * from " + c.getSimpleName() + " " + order + " limit " + pageStart + "," + pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String rule, int pageStart, int pageNum, String order) {
		String sql = "select * from " + c.getSimpleName() + " " + rule + " " + order + " limit " + pageStart + ","
				+ pageNum;
		return quaryBody(c, sql);
	}

	@Override
	public List<Object> quaryByPage(Class<?> c, String rule, int pageStart, int pageNum) {
		String sql = "select * from " + c.getSimpleName() + " " + rule + " limit " + pageStart + "," + pageNum;
		return quaryBody(c, sql);
	}

	private List<Object> quaryBody(Class<?> c, String sql) {
		// System.out.println(sql);
		List<Object> list = new ArrayList<Object>();
		Field[] fields = c.getDeclaredFields();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Constructor<?> constructor = c.getConstructor();
				Object obj = constructor.newInstance();
				for (int i = 1; i < fields.length; i++) {
					fields[i].setAccessible(true);
					fields[i].set(obj, resultSet.getObject(fields[i].getName()));
				}
				list.add(obj);
			}
		} catch (SQLException | NoSuchMethodException | SecurityException | InstantiationException
				| IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}
		return list;
	}

	@Override
	public int total(Class<?> c, String key, String value) {
		String sql = "select count(*) from " + c.getSimpleName() + " where " + key + "='" + value + "'";
		return totalBody(c, sql);
	}

	@Override
	public int total(Class<?> c, String key, long value) {
		String sql = "select count(*) from " + c.getSimpleName() + " where " + key + "=" + value;
		return totalBody(c, sql);
	}

	@Override
	public int total(Class<?> c, String key, int value) {
		String sql = "select count(*) from " + c.getSimpleName() + " where " + key + "=" + value;
		return totalBody(c, sql);
	}

	@Override
	public int total(Class<?> c, String rule) {
		String sql = "select count(*) from " + c.getSimpleName() + " " + rule;
		return totalBody(c, sql);
	}

	@Override
	public int total(Class<?> c) {
		String sql = "select count(*) from " + c.getSimpleName();
		return totalBody(c, sql);
	}

	private int totalBody(Class<?> c, String sql) {
		int sum = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				sum = resultSet.getInt(1);
			}
		} catch (SQLException | SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);
		}
		return sum;
	}

	@Override
	public boolean update(Object obj, String key, String value) {
		Class<?> c = (Class<?>) obj.getClass();
		Field[] fields = c.getDeclaredFields();
		StringBuffer sql = new StringBuffer("update " + c.getSimpleName() + " set ");
		for (int i = 1; i < fields.length; i++) {
			sql.append(fields[i].getName() + "=?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where " + key + "='" + value + "'");
		return updateBody(obj, sql.toString(), fields);
	}

	@Override
	public boolean update(Object obj, String key, long value) {
		Class<?> c = (Class<?>) obj.getClass();
		Field[] fields = c.getDeclaredFields();
		return updateBody(obj, getUpdateSql(c, fields, key, value), fields);
	}
	
	@Override
	public boolean update(Object obj, String key, int value) {
		Class<?> c = (Class<?>) obj.getClass();
		Field[] fields = c.getDeclaredFields();
		return updateBody(obj, getUpdateSql(c, fields, key, value), fields);
	}
	
	private String getUpdateSql(Class<?> c, Field[] fields, String key, long value) {
		StringBuffer sql = new StringBuffer("update " + c.getSimpleName() + " set ");
		for (int i = 1; i < fields.length; i++) {
			sql.append(fields[i].getName() + "=?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(" where " + key + "=" + value);
		return sql.toString();
	}

	private boolean updateBody(Object obj, String sql, Field[] fields) {
		// System.out.println(sql);
		boolean flag = false;
		try {
			preparedStatement = connection.prepareStatement(sql.toString());
			for (int i = 1; i < fields.length; i++) {
				fields[i].setAccessible(true);
				preparedStatement.setObject(i, fields[i].get(obj));
				// System.out.println(i - 1 + ":" + fields[i].get(obj));
			}
			flag = preparedStatement.executeUpdate() > 0 ? true : false;
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} finally {
			closeStream();
			DbUtil.getInstance().closeConnection(connection);

		}
		return flag;

	}

	public Connection getConnection() {
		return connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}


}
