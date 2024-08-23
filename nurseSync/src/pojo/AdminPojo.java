package pojo;

public class AdminPojo {
	private int adminId;
	private String username;
	private String password; // Consider secure storage for passwords
	private String email;
	private String phoneNumber;
	private String role; // E.g., Admin, Super Admin

	public AdminPojo(int adminId, String username, String password, String email, String phoneNumber, String role) {
		this.adminId = adminId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "AdminPojo [adminId=" + adminId + ", username=" + username + ", password=" + password + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", role=" + role + "]";
	}

}
