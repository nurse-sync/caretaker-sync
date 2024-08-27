package pojo;

public class MemberPojo {
	private int memberId;
	private String memberName; //name as per identification card
	private int memberAge;
	private String memberGender;
	private String memberIdentificationCardUrl;

	public MemberPojo(int memberId, String memberName, int memberAge, String memberGender, String memberIdentificationCardUrl ) {
		this.memberId = memberId;
        this.memberName = memberName;
        this.memberAge = memberAge;
        this.memberGender = memberGender;
        this.memberIdentificationCardUrl = memberIdentificationCardUrl;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public int getMemberAge() {
		return memberAge;
	}

	public void setMemberAge(int memberAge) {
		this.memberAge = memberAge;
	}

	public String getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(String memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberIdentificationCardUrl() {
		return memberIdentificationCardUrl;
	}

	public void setMemberIdentificationCardUrl(String memberIdentificationCardUrl) {
		this.memberIdentificationCardUrl = memberIdentificationCardUrl;
	}

	@Override
	public String toString() {
		return "MemberPojo [memberId=" + memberId + ", memberName=" + memberName + ", memberAge=" + memberAge
				+ ", memberGender=" + memberGender + ", memberIdentificationCardUrl=" + memberIdentificationCardUrl
				+ "]";
	}
}
