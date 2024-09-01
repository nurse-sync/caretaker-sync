package model;

public class MemberPojo {
	private int memberId;
    private String fullName;
    private int age;
    private String gender;
    private String identificationUrl;
    private int adminId;
    private int statusId;

    public MemberPojo(int memberId, String fullName, int age, String gender, String identificationUrl, int adminId, int statusId) {
        this.memberId = memberId;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.identificationUrl = identificationUrl;
        this.adminId = adminId;
        this.statusId = statusId;
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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "MemberPojo [memberId=" + memberId + ", fullName=" + fullName + ", age=" + age + ", gender=" + gender
				+ ", identificationUrl=" + identificationUrl + ", adminId=" + adminId + ", statusId=" + statusId + "]";
	}

}
