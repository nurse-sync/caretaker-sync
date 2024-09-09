package com.example.rest_care_plus.pojo;

public class ServiceProviderCategoryPojo {
	private int spCategoryId;
	private String categoryName;

	public ServiceProviderCategoryPojo(int spCategoryId, String categoryName) {
		super();
		this.spCategoryId = spCategoryId;
		this.categoryName = categoryName;
	}

	public int getSpCategoryId() {
		return spCategoryId;
	}

	public void setSpCategoryId(int spCategoryId) {
		this.spCategoryId = spCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "ServiceProviderCategoryPojo [spCategoryId=" + spCategoryId + ", categoryName=" + categoryName + "]";
	}

}
