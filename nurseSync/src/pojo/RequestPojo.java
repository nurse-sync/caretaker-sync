package pojo;

import java.sql.Date;

public class RequestPojo {
	private int requestId;
	private int userId;
	private int caretakerId;
	private String status; // e.g., "Pending", "Accepted", "Rejected"
	private String serviceLocation;
	private String patientName;
	private int patientAge;
	private String patientGender;
	private Date startDate;
	private Date endDate;

// include patient crud operations 
// patient name - like in aadhar card 
// verification - for caretaker, patients - approval by admin 
// user acc - list of service-taker  
	
	public RequestPojo(int requestId, int userId, int caretakerId, String status, String serviceLocation,
			String patientName, int patientAge, String patientGender, Date startDate, Date endDate) {
		this.requestId = requestId;
		this.userId = userId;
		this.caretakerId = caretakerId;
		this.status = status;
		this.serviceLocation = serviceLocation;
		this.patientName = patientName;
		this.patientAge = patientAge;
		this.patientGender = patientGender;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getRequestId() {
		return requestId;
	}


	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getCaretakerId() {
		return caretakerId;
	}


	public void setCaretakerId(int caretakerId) {
		this.caretakerId = caretakerId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getServiceLocation() {
		return serviceLocation;
	}


	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}


	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public int getPatientAge() {
		return patientAge;
	}


	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}


	public String getPatientGender() {
		return patientGender;
	}


	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	@Override
	public String toString() {
		return "RequestPojo [requestId=" + requestId + ", userId=" + userId + ", caretakerId=" + caretakerId
				+ ", status=" + status + ", serviceLocation=" + serviceLocation + ", patientName=" + patientName
				+ ", patientAge=" + patientAge + ", patientGender=" + patientGender + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}	

}
