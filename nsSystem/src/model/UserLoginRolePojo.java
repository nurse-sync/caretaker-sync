package model;

public class UserLoginRolePojo {
	private int userId;
    private int roleId;

    public UserLoginRolePojo(int userId, int roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "UserLoginRolePojo [userId=" + userId + ", roleId=" + roleId + "]";
	}
}
