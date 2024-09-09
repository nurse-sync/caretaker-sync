package model;

public class AddressPojo {
	private int addressId;
	private String addressFlatNumber;
	private String addressHouseNumber;
	private String addressStreetName;
	private String addressLocality;
	private String addressDistrict;
	private int addressPincode;
	private String addressCountry;

	public AddressPojo(int addressId, String addressFlatNumber, String addressHouseNumber, String addressStreetName,
			String addressLocality, String addressDistrict, int addressPincode, String addressCountry) {
		this.addressId = addressId;
		this.addressFlatNumber = addressFlatNumber;
		this.addressHouseNumber = addressHouseNumber;
		this.addressStreetName = addressStreetName;
		this.addressLocality = addressLocality;
		this.addressDistrict = addressDistrict;
		this.addressPincode = addressPincode;
		this.addressCountry = addressCountry;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddressFlatNumber() {
		return addressFlatNumber;
	}

	public void setAddressFlatNumber(String addressFlatNumber) {
		this.addressFlatNumber = addressFlatNumber;
	}

	public String getAddressHouseNumber() {
		return addressHouseNumber;
	}

	public void setAddressHouseNumber(String addressHouseNumber) {
		this.addressHouseNumber = addressHouseNumber;
	}

	public String getAddressStreetName() {
		return addressStreetName;
	}

	public void setAddressStreetName(String addressStreetName) {
		this.addressStreetName = addressStreetName;
	}

	public String getAddressLocality() {
		return addressLocality;
	}

	public void setAddressLocality(String addressLocality) {
		this.addressLocality = addressLocality;
	}

	public String getAddressDistrict() {
		return addressDistrict;
	}

	public void setAddressDistrict(String addressDistrict) {
		this.addressDistrict = addressDistrict;
	}

	public int getAddressPincode() {
		return addressPincode;
	}

	public void setAddressPincode(int addressPincode) {
		this.addressPincode = addressPincode;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}

	@Override
	public String toString() {
		return "AddressPojo [addressId=" + addressId + ", addressFlatNumber=" + addressFlatNumber
				+ ", addressHouseNumber=" + addressHouseNumber + ", addressStreetName=" + addressStreetName
				+ ", addressLocality=" + addressLocality + ", addressDistrict=" + addressDistrict + ", addressPincode="
				+ addressPincode + ", addressCountry=" + addressCountry + "]";
	}
}
