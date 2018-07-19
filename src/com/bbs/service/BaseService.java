package com.bbs.service;

import java.sql.Connection;
import java.util.List;

import com.bbs.dao.BaseDao;
import com.bbs.daoImpl.BaseDaoImpl;
import com.bbs.util.DbUtil;

public class BaseService {

	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	private BaseDao baseDao;

	public BaseService() {
		super();
		connection = DbUtil.getInstance().getConnetion();
		baseDao = new BaseDaoImpl(connection);
	}

	// 增加一条数据
	public boolean add(Object obj) {
		return baseDao.add(obj);
	}

	// 数据是否存在
	public boolean isExist(Class<?> c, String str) {
		return baseDao.isExist(c, str);
	}

	// 根据id删除数据
	public boolean deleteById(Class<?> c, long id) {
		return baseDao.deleteById(c, id);
	}

	// 获取所有数据
	public List<?> quaryAll(Class<?> c) {
		return baseDao.quaryAll(c);
	}

	public List<?> quaryAll(Class<?> c, String order) {
		return baseDao.quaryAll(c, order);
	}

	public List<?> quaryAllCustom(Class<?> c, String custom) {
		return baseDao.quaryAllCustom(c, custom);
	}

	// 根据id查询数据

	public Object quaryById(Class<?> c, long id) {
		return baseDao.quaryById(c, id);
	}

	// 更新数据

	public boolean update(Object obj) {
		return baseDao.update(obj);
	}

	public boolean update(Object obj, String key, String value) {
		return baseDao.update(obj, key, value);
	}

	public boolean update(Object obj, String key, long value) {
		return baseDao.update(obj, key, value);
	}

	public boolean update(Object obj, String key, int value) {
		return baseDao.update(obj, key, value);
	}

	// 查询

	public List<?> quaryAllByPram(Class<?> c, String key, String value) {
		return baseDao.quaryByParm(c, key, value);
	}

	public List<?> quaryAllByPram(Class<?> c, String key, int value) {
		return baseDao.quaryByParm(c, key, value);
	}

	public List<?> quaryAllByPram(Class<?> c, String key, long value) {
		return baseDao.quaryByParm(c, key, value);
	}

	public List<?> quaryAllByPram(Class<?> c, String key, String value, String order) {
		return baseDao.quaryByParm(c, key, value, order);
	}

	public List<?> quaryAllByPram(Class<?> c, String key, int value, String order) {
		return baseDao.quaryByParm(c, key, value, order);
	}

	public List<?> quaryAllByPram(Class<?> c, String key, long value, String order) {
		return baseDao.quaryByParm(c, key, value, order);
	}

	// 分页查询

	public List<?> quarryByPage(Class<?> c, String key, String value, int curPage, int rowsPrePage) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage);
	}

	public List<?> quarryByPage(Class<?> c, String key, long value, int curPage, int rowsPrePage) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage);
	}

	public List<?> quarryByPage(Class<?> c, String key, int value, int curPage, int rowsPrePage) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage);
	}

	public List<?> quarryByPage(Class<?> c, String key, String value, int curPage, int rowsPrePage, String order) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage, order);
	}

	public List<?> quarryByPage(Class<?> c, String key, long value, int curPage, int rowsPrePage, String order) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage, order);
	}

	public List<?> quarryByPage(Class<?> c, String key, int value, int curPage, int rowsPrePage, String order) {
		return baseDao.quaryByPage(c, key, value, curPage, rowsPrePage, order);
	}

	public List<?> quarryByPage(Class<?> c, int curPage, int rowsPrePage, String order) {
		return baseDao.quaryByPage(c, curPage, rowsPrePage, order);
	}

	public List<?> quarryByPage(Class<?> c, String rule, int curPage, int rowsPrePage, String order) {
		return baseDao.quaryByPage(c, rule, curPage, rowsPrePage, order);
	}

	public List<?> quarryByPage(Class<?> c, String rule, int curPage, int rowsPrePage) {
		return baseDao.quaryByPage(c, rule, curPage, rowsPrePage);
	}

	// 获取表格数据条数
	public int total(Class<?> c, String key, String value) {
		return baseDao.total(c, key, value);
	}

	public int total(Class<?> c, String key, long value) {
		return baseDao.total(c, key, value);
	}

	public int total(Class<?> c, String key, int value) {
		return baseDao.total(c, key, value);
	}

	public int total(Class<?> c, String rule) {
		return baseDao.total(c, rule);
	}

	public int total(Class<?> c) {
		return baseDao.total(c);
	}

}
