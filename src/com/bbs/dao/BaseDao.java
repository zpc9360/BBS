package com.bbs.dao;

import java.util.List;

public interface BaseDao {

	//增加一条数据
	public boolean add(Object obj);
	//查询数据是否存在
	public boolean isExist(Class<?> c,String str);
	//根据id删除数据
	public boolean deleteById(Class<?> c,long id);
	//获取所有数据
	public List<?> quaryAll(Class<?> c);
	public List<?> quaryAllCustom(Class<?> c,String custom);
	public List<?> quaryAll(Class<?> c,String order);
	//根据id查询数据
	public Object quaryById(Class<?> c,long id);
	//更新数据
	//update TABLE set ...=... , ...=... , ...   where userName(key) = jack （value）
	public boolean update(Object obj);//通过id修改
	public boolean update(Object obj,String key,String value);//通过String类型字段修改
	public boolean update(Object obj,String key,long value);//通过long类型字段修改
	public boolean update(Object obj,String key,int value);//通过int类型字段修改
	//通过字段名查询数据
	//select * from TABLE where userName(key) = Jack(value)
	//select * from TABLE where topicId(key) = 1(value)
	public List<?> quaryByParm(Class<?> c,String key, String value);//通过String类型字段查询
	public List<?> quaryByParm(Class<?> c,String key, long value);//通过long类型字段查询
	public List<?> quaryByParm(Class<?> c,String key, int value);//通过int类型字段查询
	//通过字段名查询数据,并排序
	//select * from TABLE where userName(key) = Jack(value) order by order(String)
	//select * from TABLE where topicId(key) = 1(value) order by order(String)
	public List<?> quaryByParm(Class<?> c,String key, String value, String order);//通过String类型字段查询，并按照order字段排序
	public List<?> quaryByParm(Class<?> c,String key, long value, String order);//通过long类型字段查询，并按照order字段排序
	public List<?> quaryByParm(Class<?> c,String key, int value, String order);//通过int类型字段查询，并按照order字段排序
	//分页查询
	public List<?> quaryByPage(Class<?> c, String rule, int pageStart, int pageNum);//*****自定义分页查询，按照limit pageStart, pageNum来分页查询
	public List<?> quaryByPage(Class<?> c, String key, String value, int pageStart, int pageNum);//通过String类型字段，按照limit pageStart, pageNum来分页查询
	public List<?> quaryByPage(Class<?> c, String key, long value, int pageStart, int pageNum);//通过long类型字段，按照limit pageStart, pageNum来分页查询
	public List<?> quaryByPage(Class<?> c, String key, int value, int pageStart, int pageNum);//通过int类型字段，按照limit pageStart, pageNum来分页查询
	//分页查询,并排序
	public List<?> quaryByPage(Class<?> c, String rule, int pageStart, int pageNum, String order);//*****自定义分页查询，按照limit pageStart, pageNum来分页查询,然后按照order字段排序
	public List<?> quaryByPage(Class<?> c, String key, String value, int pageStart, int pageNum, String order);//通过String类型字段，按照limit pageStart, pageNum来分页查询,然后按照order字段排序
	public List<?> quaryByPage(Class<?> c, String key, long value, int pageStart, int pageNum, String order);//通过long类型字段，按照limit pageStart, pageNum来分页查询,然后按照order字段排序
	public List<?> quaryByPage(Class<?> c, String key, int value, int pageStart, int pageNum, String order);//通过int类型字段，按照limit pageStart, pageNum来分页查询,然后按照order字段排序
	public List<?> quaryByPage(Class<?> c, int pageStart, int pageNum, String order);//按照limit pageStart, pageNum来分页查询,然后按照order字段排序
	//获取数据条数
	public int total(Class<?> c,String key, String value);//通过String类型字段获取当前表下该字段的条数
	public int total(Class<?> c,String key, long value);//通过long类型字段获取当前表下该字段的条数
	public int total(Class<?> c,String key, int value);//通过int类型字段获取当前表下该字段的条数
	public int total(Class<?> c,String rule);//获取当前限定规则下的数据条数
	public int total(Class<?> c);//获取当前表下该字段的条数
	
}
