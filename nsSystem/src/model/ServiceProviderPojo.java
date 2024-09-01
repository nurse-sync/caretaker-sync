package model;

import java.sql.Date;

public class ServiceProviderPojo {
	private int spId;
	private String gender;
	private int category;
	private double weeklySalary;
	private Date availableFrom;
	private Date availableTo;
	private int addressId;
	private int qualificationId;
	private boolean liveIn;
	private int adminId;
	private int statusId;
	private String idProofUrl;
	private Integer nurseLicenseId; // Use Integer to allow for null values

	public ServiceProviderPojo(int spId, String gender, int category, double weeklySalary, Date availableFrom,
			Date availableTo, int addressId, int qualificationId, boolean liveIn, int adminId, int statusId,
			String idProofUrl, Integer nurseLicenseId) {
		this.spId = spId;
		this.gender = gender;
		this.category = category;
		this.weeklySalary = weeklySalary;
		this.availableFrom = availableFrom;
		this.availableTo = availableTo;
		this.addressId = addressId;
		this.qualificationId = qualificationId;
		this.liveIn = liveIn;
		this.adminId = adminId;
		this.statusId = statusId;
		this.idProofUrl = idProofUrl;
		this.nurseLicenseId = nurseLicenseId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public double getWeeklySalary() {
		return weeklySalary;
	}

	public void setWeeklySalary(double weeklySalary) {
		this.weeklySalary = weeklySalary;
	}

	public Date getAvailableFrom() {
		return availableFrom;
	}

	public void setAvailableFrom(Date availableFrom) {
		this.availableFrom = availableFrom;
	}

	public Date getAvailableTo() {
		return availableTo;
	}

	public void setAvailableTo(Date availableTo) {
		this.availableTo = availableTo;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getQualificationId() {
		return qualificationId;
	}

	public void setQualificationId(int qualificationId) {
		this.qualificationId = qualificationId;
	}

	public boolean isLiveIn() {
		return liveIn;
	}

	public void setLiveIn(boolean liveIn) {
		this.liveIn = liveIn;
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

	public String getIdProofUrl() {
		return idProofUrl;
	}

	public void setIdProofUrl(String idProofUrl) {
		this.idProofUrl = idProofUrl;
	}

	public Integer getNurseLicenseId() {
		return nurseLicenseId;
	}

	public void setNurseLicenseId(Integer nurseLicenseId) {
		this.nurseLicenseId = nurseLicenseId;
	}

	@Override
	public String toString() {
		return "ServiceProviderPojo [spId=" + spId + ", gender=" + gender + ", category=" + category + ", weeklySalary="
				+ weeklySalary + ", availableFrom=" + availableFrom + ", availableTo=" + availableTo + ", addressId="
				+ addressId + ", qualificationId=" + qualificationId + ", liveIn=" + liveIn + ", adminId=" + adminId
				+ ", statusId=" + statusId + ", idProofUrl=" + idProofUrl + ", nurseLicenseId=" + nurseLicenseId + "]";
	}
}
