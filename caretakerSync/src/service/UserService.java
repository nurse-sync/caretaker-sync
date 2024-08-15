package service;

import java.util.List;

import pojo.UserPojo;

public interface UserService {
	boolean createUser(UserPojo user);
    UserPojo getUserById(int userId);
    UserPojo getUserByUsername(String username);
    List<UserPojo> getAllUsers();
    boolean updateUser(UserPojo user);
    boolean deleteUser(int userId);
}
