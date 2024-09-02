package dao;

import java.util.List;

import model.UserPojo;

public interface UserDao {
	boolean createUser(UserPojo user);

	UserPojo fetchUserById(int userId);

	List<UserPojo> fetchAllUsers();

	boolean updateUser(UserPojo user);

	boolean deleteUser(int userId);
}
