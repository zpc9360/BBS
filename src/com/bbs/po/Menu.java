package com.bbs.po;

import java.io.Serializable;

public class Menu implements Serializable {
	private static final long serialVersionUID = -2942272277242344512L;
	
	private long id;
	private String menuName;
	private String menuDetail;
	
	public String getMenuDetail() {
		return menuDetail;
	}
	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
