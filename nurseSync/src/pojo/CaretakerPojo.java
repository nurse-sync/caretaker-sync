package pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CaretakerPojo {
	private int caretakerId;
	private String name;
	private String userName;
	private String password;
	private String gender; //  Male, Female, Others
	private String category; // e.g., Nurse, Caretaker
	private double weeklyRate; // weekly rate for the services provided
	private Date availabilityFrom; // When the caretaker is available from
	private Date availabilityTo; // When the caretaker is available until
	private String location; // Location where the carretaker is available
	private String phoneNumber; // Contact number of the caretaker
	private String qualifications; // Qualifications or experience details
	private boolean liveIn; // Whether the caretaker is willing to live in
	private String status; // New field: e.g., "Available", "Booked"
	private List<RequestPojo> requests; // List to store requests

	public CaretakerPojo(int caretakerId, String name, String userName, String password, String gender, String category,
			double weeklyRate, Date availabilityFrom, Date availabilityTo, String location, String phoneNumber,
			String qualifications, boolean liveIn, String status) {
		this.caretakerId = caretakerId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
		this.category = category;
		this.weeklyRate = weeklyRate;
		this.availabilityFrom = availabilityFrom;
		this.availabilityTo = availabilityTo;
		this.location = location;
		this.phoneNumber = phoneNumber;
		this.qualifications = qualifications;
		this.liveIn = liveIn;
		this.status = status;
		this.requests = new ArrayList<>();
	}

	public int getCaretakerId() {
		return caretakerId;
	}

	public void setCaretakerId(int caretakerId) {
		this.caretakerId = caretakerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getWeeklyRate() {
		return weeklyRate;
	}

	public void setWeeklyRate(double weeklyRate) {
		this.weeklyRate = weeklyRate;
	}

	public Date getAvailabilityFrom() {
		return availabilityFrom;
	}

	public void setAvailabilityFrom(Date availabilityFrom) {
		this.availabilityFrom = availabilityFrom;
	}

	public Date getAvailabilityTo() {
		return availabilityTo;
	}

	public void setAvailabilityTo(Date availabilityTo) {
		this.availabilityTo = availabilityTo;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public boolean getLiveIn() {
		return liveIn;
	}

	public void setLiveIn(boolean liveIn) {
		this.liveIn = liveIn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RequestPojo> getRequests() {
		return requests;
	}

	public void setRequests(List<RequestPojo> requests) {
		this.requests = requests;
	}

	@Override
	public String toString() {
		return "CaretakerPojo [caretakerId=" + caretakerId + ", name=" + name + ", userName=" + userName + ", password="
				+ password + ", gender=" + gender + ", category=" + category + ", weeklyRate=" + weeklyRate
				+ ", availabilityFrom=" + availabilityFrom + ", availabilityTo=" + availabilityTo + ", location="
				+ location + ", phoneNumber=" + phoneNumber + ", qualifications=" + qualifications + ", liveIn="
				+ liveIn + ", status=" + status + ", requests=" + requests + "]";
	}
}
