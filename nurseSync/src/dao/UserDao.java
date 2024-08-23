package dao;

import java.util.List;

import pojo.UserPojo;

public interface UserDao {
	boolean addUser(UserPojo user);

	UserPojo getUserById(int userId);

	List<UserPojo> getAllUsers();

	boolean updateUser(UserPojo user);

	boolean deleteUser(int userId);

	UserPojo getUserByUsername(String username);

}
