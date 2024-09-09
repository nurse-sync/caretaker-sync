package com.example.rest_care_plus.pojo;

public class AddressPojo {
	private int addressId;
	private String flatNumber;
	private String houseNumber;
	private String streetName;
	private String locality;
	private String district;
	private int pincode;
	private String country;

	public AddressPojo(int addressId, String flatNumber, String houseNumber, String streetName, String locality,
			String district, int pincode, String country) {
		super();
		this.addressId = addressId;
		this.flatNumber = flatNumber;
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.locality = locality;
		this.district = district;
		this.pincode = pincode;
		this.country = country;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getFlatNumber() {
		return flatNumber;
	}

	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "AddressPojo [addressId=" + addressId + ", flatNumber=" + flatNumber + ", houseNumber=" + houseNumber
				+ ", streetName=" + streetName + ", locality=" + locality + ", district=" + district + ", pincode="
				+ pincode + ", country=" + country + "]";
	}

}
