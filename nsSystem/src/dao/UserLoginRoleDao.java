package dao;

import java.util.List;

import model.UserLoginRolePojo;

public interface UserLoginRoleDao {
	boolean assignRoleToUser(UserLoginRolePojo userRole);

	List<String> fetchRolesByUserId(int userId);

	boolean removeRoleFromUser(UserLoginRolePojo userRole);
}
