package dao;

import java.util.List;

import pojo.UserPojo;

public interface UserDao {
	// Adds a new user to the collection
    boolean addUser(UserPojo user);
    
    // Retrieves a user by their ID
    UserPojo getUserById(int userId);
    
    // Retrieves all users in the collection
    List<UserPojo> getAllUsers();
    
    // Updates an existing user's information
    boolean updateUser(UserPojo user);
    
    // Removes a user from the collection by their ID
    boolean deleteUser(int userId);
    
    UserPojo getUserByUsername(String username);
    
}
