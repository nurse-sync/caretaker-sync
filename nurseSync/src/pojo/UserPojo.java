package pojo;

import java.util.ArrayList;
import java.util.List;

public class UserPojo {
	private int userId;
	private String name;
	private String username;
	private String password;
	private String email;
	private String address;
	private String phoneNumber;
	private List<Integer> sentRequests;

	public UserPojo(int userId, String name, String username, String password, String email, String phoneNumber,
			String address) {
		this.userId = userId;
		this.name = name;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.sentRequests = new ArrayList<>(); 
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Integer> getSentRequests() {
		return sentRequests;
	}

	public void setSentRequests(List<Integer> sentRequests) {
		this.sentRequests = sentRequests;
	}

	public void addSentRequest(int requestId) {
		this.sentRequests.add(requestId);
	}

	@Override
	public String toString() {
		return "UserPojo [userId=" + userId + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", email=" + email + ", address=" + address + ", phoneNumber=" + phoneNumber + ", sentRequests="
				+ sentRequests + "]";
	}

}
