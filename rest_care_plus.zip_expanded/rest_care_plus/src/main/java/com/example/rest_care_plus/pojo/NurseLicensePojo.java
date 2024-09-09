package com.example.rest_care_plus.pojo;

public class NurseLicensePojo {
	private int licenseId;
	private ServiceProviderPojo serviceProvider;
	private String licenseUrl;

	public NurseLicensePojo(int licenseId, ServiceProviderPojo serviceProvider, String licenseUrl) {
		super();
		this.licenseId = licenseId;
		this.serviceProvider = serviceProvider;
		this.licenseUrl = licenseUrl;
	}

	public int getLicenseId() {
		return licenseId;
	}

	public void setLicenseId(int licenseId) {
		this.licenseId = licenseId;
	}

	public ServiceProviderPojo getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProviderPojo serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public String getLicenseUrl() {
		return licenseUrl;
	}

	public void setLicenseUrl(String licenseUrl) {
		this.licenseUrl = licenseUrl;
	}

	@Override
	public String toString() {
		return "NurseLicensePojo [licenseId=" + licenseId + ", serviceProvider=" + serviceProvider + ", licenseUrl="
				+ licenseUrl + "]";
	}

}
