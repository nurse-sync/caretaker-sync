package model;

public class RolePojo {
	private int roleId;
    private String roleName;

    public RolePojo(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "RolePojo [roleId=" + roleId + ", roleName=" + roleName + "]";
	}
}
