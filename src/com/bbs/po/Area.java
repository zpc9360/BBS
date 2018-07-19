package com.bbs.po;

import java.io.Serializable;

public class Area implements Serializable {

	private static final long serialVersionUID = -7933087622480368500L;
	
	private long id;
	private String areaName;
	private long userId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}


}
