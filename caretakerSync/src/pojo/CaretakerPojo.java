package pojo;

import java.time.LocalDateTime;

public class CaretakerPojo {
   	private int caretakerId;
    private String name;
    private String password;
    private String gender; // e.g., Male, Female, Others
    private String category; // e.g., Nurse, Caretaker
    private double weeklyRate; // weekly rate for the services provided
    private LocalDateTime availabilityFrom; // When the caretaker is available from
    private LocalDateTime availabilityTo; // When the caretaker is available until
    private String location; // Location where the carretaker is available
    private String phoneNumber; // Contact number of the caretaker
    private String qualifications; // Qualifications or experience details
    private boolean isLiveIn; // Whether the caretaker is willing to live in
    private String status; // New field: e.g., "Available", "Booked"
    
    public CaretakerPojo(int caretakerId, String name, String password, String gender, String category, double weeklyRate, 
            LocalDateTime availabilityFrom, LocalDateTime availabilityTo, String location, 
            String phoneNumber, String qualifications, boolean isLiveIn, String status) {
		 this.caretakerId = caretakerId;
		 this.name = name;
		 this.password = password;
		 this.gender = gender;
		 this.category = category;
		 this.weeklyRate = weeklyRate;
		 this.availabilityFrom = availabilityFrom;
		 this.availabilityTo = availabilityTo;
		 this.location = location;
		 this.phoneNumber = phoneNumber;
		 this.qualifications = qualifications;
		 this.isLiveIn = isLiveIn;
		 this.status = status;
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


	public LocalDateTime getAvailabilityFrom() {
		return availabilityFrom;
	}


	public void setAvailabilityFrom(LocalDateTime availabilityFrom) {
		this.availabilityFrom = availabilityFrom;
	}


	public LocalDateTime getAvailabilityTo() {
		return availabilityTo;
	}


	public void setAvailabilityTo(LocalDateTime availabilityTo) {
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


	public boolean getIsLiveIn() {
		return isLiveIn;
	}


	public void setIsLiveIn(boolean isLiveIn) {
		this.isLiveIn = isLiveIn;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
        this.status = status;
    }


	@Override
	public String toString() {
		return "CaretakerPojo [caretakerId=" + caretakerId + ", name=" + name + ", password=" + password + ", gender="
				+ gender + ", category=" + category + ", weeklyRate=" + weeklyRate + ", availabilityFrom="
				+ availabilityFrom + ", availabilityTo=" + availabilityTo + ", location=" + location + ", phoneNumber="
				+ phoneNumber + ", qualifications=" + qualifications + ", isLiveIn=" + isLiveIn + ", status=" + status
				+ "]";
	}	
}
