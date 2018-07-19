package com.bbs.po;

import java.io.Serializable;

public class Section implements Serializable {

	private static final long serialVersionUID = -6492675686641862585L;
	
	private long id;
	private String sectionName;
	private long areaId;
	private long userId;
	private long sectionSum;
	
	public long getSectionSum() {
		return sectionSum;
	}
	public void setSectionSum(long sectionSum) {
		this.sectionSum = sectionSum;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public long getAreaId() {
		return areaId;
	}
	public void setAreaId(long areaId) {
		this.areaId = areaId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}


}
