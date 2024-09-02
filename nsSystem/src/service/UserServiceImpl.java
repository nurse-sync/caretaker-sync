package service;

import java.util.List;

import dao.UserDao;
import dao.UserDaoImpl;
import model.UserPojo;

public class UserServiceImpl implements UserService {
	private UserDao userDao;

	public UserServiceImpl() {
		this.userDao = new UserDaoImpl(); 
	}

	@Override
	public boolean createUser(UserPojo user) throws Exception {
		try {
			return userDao.createUser(user);
		} catch (Exception e) {
			throw new Exception("Error creating user: " + e.getMessage(), e);
		}
	}

	@Override
	public UserPojo fetchUserById(int userId) throws Exception {
		try {
			return userDao.fetchUserById(userId);
		} catch (Exception e) {
			throw new Exception("Error fetching user by ID: " + e.getMessage(), e);
		}
	}

	@Override
	public List<UserPojo> fetchAllUsers() throws Exception {
		try {
			return userDao.fetchAllUsers();
		} catch (Exception e) {
			throw new Exception("Error fetching all users: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean updateUser(UserPojo user) throws Exception {
		try {
			return userDao.updateUser(user);
		} catch (Exception e) {
			throw new Exception("Error updating user: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean deleteUser(int userId) throws Exception {
		try {
			return userDao.deleteUser(userId);
		} catch (Exception e) {
			throw new Exception("Error deleting user: " + e.getMessage(), e);
		}
	}
}
