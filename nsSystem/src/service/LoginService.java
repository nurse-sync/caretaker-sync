package service;

import model.LoginPojo;

public interface LoginService {
	LoginPojo getUserById(int userId);

	LoginPojo getUserByUserName(String userName);

	boolean addUser(LoginPojo user);

	boolean updateUser(LoginPojo user);

	boolean deleteUser(int userId);
}
