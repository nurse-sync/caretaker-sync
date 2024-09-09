package com.example.rest_care_plus.pojo;

public class QualificationPojo {
	private int spQualificationId;
	private ServiceProviderPojo serviceProvider;
	private int experienceInYears;
	private String roleName;

	public QualificationPojo(int spQualificationId, ServiceProviderPojo serviceProvider, int experienceInYears,
			String roleName) {
		super();
		this.spQualificationId = spQualificationId;
		this.serviceProvider = serviceProvider;
		this.experienceInYears = experienceInYears;
		this.roleName = roleName;
	}

	public int getSpQualificationId() {
		return spQualificationId;
	}

	public void setSpQualificationId(int spQualificationId) {
		this.spQualificationId = spQualificationId;
	}

	public ServiceProviderPojo getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProviderPojo serviceProvider) {
		this.serviceProvider = serviceProvider;
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
		return "QualificationPojo [spQualificationId=" + spQualificationId + ", serviceProvider=" + serviceProvider
				+ ", experienceInYears=" + experienceInYears + ", roleName=" + roleName + "]";
	}

}
