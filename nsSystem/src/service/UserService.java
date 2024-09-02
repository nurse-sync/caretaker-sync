package service;

import java.util.List;

import model.UserPojo;

public interface UserService {
	boolean createUser(UserPojo user) throws Exception;

	UserPojo fetchUserById(int userId) throws Exception;

	List<UserPojo> fetchAllUsers() throws Exception;

	boolean updateUser(UserPojo user) throws Exception;

	boolean deleteUser(int userId) throws Exception;
}
