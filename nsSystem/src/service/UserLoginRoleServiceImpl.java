package service;

import java.util.List;

import dao.UserLoginRoleDao;
import dao.UserLoginRoleDaoImpl;
import model.UserLoginRolePojo;

public class UserLoginRoleServiceImpl implements UserLoginRoleService {
	private UserLoginRoleDao userLoginRoleDao;

	public UserLoginRoleServiceImpl() {
		this.userLoginRoleDao = new UserLoginRoleDaoImpl();
	}

	@Override
	public boolean assignRoleToUser(UserLoginRolePojo userRole) throws Exception {
		try {
			return userLoginRoleDao.assignRoleToUser(userRole);
		} catch (Exception e) {
			throw new Exception("Error assigning role to user: " + e.getMessage(), e);
		}
	}

	@Override
	public List<String> fetchRolesByUserId(int userId) throws Exception {
		try {
			return userLoginRoleDao.fetchRolesByUserId(userId);
		} catch (Exception e) {
			throw new Exception("Error fetching roles by user ID: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean removeRoleFromUser(UserLoginRolePojo userRole) throws Exception {
		try {
			return userLoginRoleDao.removeRoleFromUser(userRole);
		} catch (Exception e) {
			throw new Exception("Error removing role from user: " + e.getMessage(), e);
		}
	}

}
