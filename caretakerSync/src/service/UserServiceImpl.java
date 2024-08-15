package service;

import java.util.List;

import pojo.UserPojo;
import dao.UserDao;

public class UserServiceImpl implements UserService{
	private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public boolean createUser(UserPojo user) {
        return userDao.addUser(user);

	}

	@Override
	public UserPojo getUserById(int userId) {
		return userDao.getUserById(userId);
	}

	@Override
	public List<UserPojo> getAllUsers() {
        return userDao.getAllUsers();

	}

	@Override
	public boolean updateUser(UserPojo user) {
		return userDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
	}

	@Override
	public UserPojo getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	
}
