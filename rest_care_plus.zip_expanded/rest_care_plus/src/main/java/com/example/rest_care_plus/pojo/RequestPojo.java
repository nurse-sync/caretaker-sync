package com.example.rest_care_plus.pojo;

import java.sql.Date;

public class RequestPojo {
	private int requestId;
	private UserInfoPojo clientInfo;
	private ServiceProviderPojo serviceProvider;
	private AddressPojo address;
	private Date startDate;
	private Date endDate;
	private MemberPojo member;
	private String messageToSp;
	private String messageFromSp;
	private StatusPojo status;

	public RequestPojo(int requestId, UserInfoPojo clientInfo, ServiceProviderPojo serviceProvider, AddressPojo address,
			Date startDate, Date endDate, MemberPojo member, String messageToSp, String messageFromSp,
			StatusPojo status) {
		super();
		this.requestId = requestId;
		this.clientInfo = clientInfo;
		this.serviceProvider = serviceProvider;
		this.address = address;
		this.startDate = startDate;
		this.endDate = endDate;
		this.member = member;
		this.messageToSp = messageToSp;
		this.messageFromSp = messageFromSp;
		this.status = status;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public UserInfoPojo getClientInfo() {
		return clientInfo;
	}

	public void setClientInfo(UserInfoPojo clientInfo) {
		this.clientInfo = clientInfo;
	}

	public ServiceProviderPojo getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(ServiceProviderPojo serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

	public AddressPojo getAddress() {
		return address;
	}

	public void setAddress(AddressPojo address) {
		this.address = address;
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

	public MemberPojo getMember() {
		return member;
	}

	public void setMember(MemberPojo member) {
		this.member = member;
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

	public StatusPojo getStatus() {
		return status;
	}

	public void setStatus(StatusPojo status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestPojo [requestId=" + requestId + ", clientInfo=" + clientInfo + ", serviceProvider="
				+ serviceProvider + ", address=" + address + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", member=" + member + ", messageToSp=" + messageToSp + ", messageFromSp=" + messageFromSp
				+ ", status=" + status + "]";
	}

}
