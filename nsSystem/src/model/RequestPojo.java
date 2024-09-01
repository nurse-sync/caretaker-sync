package model;

import java.sql.Date;

public class RequestPojo {
	
	private int requestId;
	private int clientId;
	private int spId;
	private int addressId;
	private Date startDate;
	private Date endDate;
	private int memberId;
	private String messageToSp;
	private String messageFromSp;
	private int statusId;

	public RequestPojo(int requestId, int clientId, int spId, int addressId, Date startDate, Date endDate, 
			int memberId, String messageToSp, String messageFromSp, int statusId) {
		 this.requestId = requestId;
		 this.clientId = clientId;
		 this.spId = spId;
		 this.addressId = addressId;
		 this.startDate = startDate;
		 this.endDate = endDate;
		 this.memberId = memberId;
		 this.messageToSp = messageToSp;
		 this.messageFromSp = messageFromSp;
		 this.statusId = statusId;	 
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public int getSpId() {
		return spId;
	}

	public void setSpId(int spId) {
		this.spId = spId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getMessageToSp() {
		return messageToSp;
	}

	public void setMessageToSp(String messageToSp) {
		this.messageToSp = messageToSp;
	}

	public String getMessageFromSp() {
		return messageFromSp;
	}

	public void setMessageFromSp(String messageFromSp) {
		this.messageFromSp = messageFromSp;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "RequestPojo [requestId=" + requestId + ", clientId=" + clientId + ", spId=" + spId + ", addressId="
				+ addressId + ", startDate=" + startDate + ", endDate=" + endDate + ", memberId=" + memberId
				+ ", messageToSp=" + messageToSp + ", messageFromSp=" + messageFromSp + ", statusId=" + statusId + "]";
	}
}
