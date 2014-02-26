package com.zz.test.model.user;

import java.io.Serializable;

import com.zz.bsp.core.Model;

public class UserModel extends Model {
	private static final long serialVersionUID = -826093286526668144L;
	
	private Long id;
	private String userName;
	private String userPwd;
	
	@Override
	public Serializable getPK() {
		return id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		entityMap.put("userName", userName);
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		entityMap.put("userPwd", userPwd);
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", userName=" + userName + ", userPwd="
				+ userPwd + "]";
	}
}
