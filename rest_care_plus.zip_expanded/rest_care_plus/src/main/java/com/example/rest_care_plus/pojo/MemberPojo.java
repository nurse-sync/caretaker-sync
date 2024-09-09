package com.example.rest_care_plus.pojo;

public class MemberPojo {
	private int memberId;
	private String fullName;
	private int age;
	private String gender;
	private String identificationUrl;
	private UserInfoPojo admin;
	private StatusPojo status;

	public MemberPojo(int memberId, String fullName, int age, String gender, String identificationUrl,
			UserInfoPojo admin, StatusPojo status) {
		super();
		this.memberId = memberId;
		this.fullName = fullName;
		this.age = age;
		this.gender = gender;
		this.identificationUrl = identificationUrl;
		this.admin = admin;
		this.status = status;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIdentificationUrl() {
		return identificationUrl;
	}

	public void setIdentificationUrl(String identificationUrl) {
		this.identificationUrl = identificationUrl;
	}

	public UserInfoPojo getAdmin() {
		return admin;
	}

	public void setAdmin(UserInfoPojo admin) {
		this.admin = admin;
	}

	public StatusPojo getStatus() {
		return status;
	}

	public void setStatus(StatusPojo status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MemberPojo [memberId=" + memberId + ", fullName=" + fullName + ", age=" + age + ", gender=" + gender
				+ ", identificationUrl=" + identificationUrl + ", admin=" + admin + ", status=" + status + "]";
	}

}
