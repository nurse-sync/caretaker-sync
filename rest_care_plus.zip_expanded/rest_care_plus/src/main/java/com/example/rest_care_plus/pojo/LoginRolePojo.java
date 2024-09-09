package com.example.rest_care_plus.pojo;

public class LoginRolePojo {
	private UserInfoPojo userInfo;
	private RolePojo role;

	public LoginRolePojo(UserInfoPojo userInfo, RolePojo role) {
		super();
		this.userInfo = userInfo;
		this.role = role;
	}

	public UserInfoPojo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPojo userInfo) {
		this.userInfo = userInfo;
	}

	public RolePojo getRole() {
		return role;
	}

	public void setRole(RolePojo role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginRolePojo [userInfo=" + userInfo + ", role=" + role + "]";
	}

}
