package com.bbs.po;

import java.io.Serializable;

public class Authority implements Serializable {

	private static final long serialVersionUID = -6702360659981631747L;

	private long id;
	private String authorityName;
	private String authorityDetail;
	private long roleId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	public String getAuthorityDetail() {
		return authorityDetail;
	}
	public void setAuthorityDetail(String authorityDetail) {
		this.authorityDetail = authorityDetail;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

}
