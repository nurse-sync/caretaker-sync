package service;

import java.util.List;

import model.RolePojo;

public interface RoleService {
	int addRole(RolePojo rolePojo);

	int updateRole(RolePojo rolePojo);

	int deleteRole(int roleId);

	RolePojo getRoleById(int roleId);

	List<RolePojo> getAllRoles();
}
