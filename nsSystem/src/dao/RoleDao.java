package dao;

import java.util.List;

import model.RolePojo;

public interface RoleDao {
	int addRole(RolePojo rolePojo);

	int updateRole(RolePojo rolePojo);

	int deleteRole(int roleId);

	RolePojo getRoleById(int roleId);

	List<RolePojo> getAllRoles();
}
