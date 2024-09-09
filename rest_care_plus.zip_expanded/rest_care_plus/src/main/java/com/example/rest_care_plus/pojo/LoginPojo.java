package com.example.rest_care_plus.pojo;

public class LoginPojo {
	private UserInfoPojo userInfo;
	private String userName;
	private String password;

	public LoginPojo(UserInfoPojo userInfo, String userName, String password) {
		super();
		this.userInfo = userInfo;
		this.userName = userName;
		this.password = password;
	}

	public UserInfoPojo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoPojo userInfo) {
		this.userInfo = userInfo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginPojo [userInfo=" + userInfo + ", userName=" + userName + ", password=" + password + "]";
	}
}
