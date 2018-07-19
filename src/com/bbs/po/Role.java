package com.bbs.po;

import java.io.Serializable;

public class Role implements Serializable {

	private static final long serialVersionUID = -8916804072202271117L;

	private long id;
	private String roleName;
	private long roleLevel;
	private long upLevel;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getRoleLevel() {
		return roleLevel;
	}

	public void setRoleLevel(long roleLevel) {
		this.roleLevel = roleLevel;
	}

	public long getUpLevel() {
		return upLevel;
	}

	public void setUpLevel(long upLevel) {
		this.upLevel = upLevel;
	}

}
