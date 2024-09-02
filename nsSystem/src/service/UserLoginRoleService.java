package service;

import java.util.List;

import model.UserLoginRolePojo;

public interface UserLoginRoleService {
	boolean assignRoleToUser(UserLoginRolePojo userRole) throws Exception;

	List<String> fetchRolesByUserId(int userId) throws Exception;

	boolean removeRoleFromUser(UserLoginRolePojo userRole) throws Exception;
}
