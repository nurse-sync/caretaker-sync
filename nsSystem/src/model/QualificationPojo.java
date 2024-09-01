package model;

public class QualificationPojo {
	private int spQualificationId;
    private int spId;
    private int experienceInYears;
    private String roleName;

    public QualificationPojo(int spQualificationId, int spId, int experienceInYears, String roleName) {
        this.spQualificationId = spQualificationId;
        this.spId = spId;
        this.experienceInYears = experienceInYears;
        this.roleName = roleName;
    }

	public int getSpQualificationId() {
		return spQualificationId;
	}

	public void setSpQualificationId(int spQualificationId) {
		this.spQualificationId = spQualificationId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public int getExperienceInYears() {
		return experienceInYears;
	}

	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "QualificationPojo [spQualificationId=" + spQualificationId + ", spId=" + spId + ", experienceInYears="
				+ experienceInYears + ", roleName=" + roleName + "]";
	}

}
