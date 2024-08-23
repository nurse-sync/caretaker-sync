package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.UserPojo;

public class UserDaoCollectionImpl implements UserDao {
	private Map<Integer, UserPojo> userData = new HashMap<>();

	@Override
	public boolean addUser(UserPojo user) {
		int newId = userData.size() + 1;
		UserPojo newUser = new UserPojo(newId, user.getName(), user.getUsername(), user.getPassword(), user.getEmail(),
				user.getPhoneNumber(), user.getAddress());
		userData.put(newId, newUser);
		return true;
	}

	@Override
	public UserPojo getUserById(int userId) {
		return userData.get(userId);
	}

	@Override
	public List<UserPojo> getAllUsers() {
		return new ArrayList<>(userData.values());
	}

	@Override
	public boolean updateUser(UserPojo user) {
		if (userData.containsKey(user.getUserId())) {
			// Create a new User object to prevent modification of ID
			UserPojo updatedUser = new UserPojo(user.getUserId(), user.getName(), user.getUsername(),
					user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
			userData.put(user.getUserId(), updatedUser); // Update the user in the HashMap
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		if (userData.containsKey(userId)) {
			userData.remove(userId);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserPojo getUserByUsername(String username) {
		return userData.values().stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
	}

}
