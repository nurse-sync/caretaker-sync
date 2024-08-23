package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import exceptions.GlobalExceptionHandler;
import pojo.UserPojo;

public class UserDaoCollectionImpl implements UserDao {
	private Map<Integer, UserPojo> userData = new HashMap<>();

	@Override
	public boolean addUser(UserPojo user) {
		try {
			int newId = userData.size() + 1;
			UserPojo newUser = new UserPojo(newId, user.getName(), user.getUsername(), user.getPassword(),
					user.getEmail(), user.getPhoneNumber(), user.getAddress());
			userData.put(newId, newUser);
			return true;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}

	}

	@Override
	public UserPojo getUserById(int userId) {
		try {
			UserPojo user = userData.get(userId);
			if (user == null) {
				throw new NoSuchElementException("User not found with ID: " + userId);
			}
			return user;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

	@Override
	public List<UserPojo> getAllUsers() {
		try {
			return new ArrayList<>(userData.values());
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return List.of(); // Return an empty list in case of an error
		}
	}

	@Override
	public boolean updateUser(UserPojo user) {
		try {
			if (userData.containsKey(user.getUserId())) {
				UserPojo updatedUser = new UserPojo(user.getUserId(), user.getName(), user.getUsername(),
						user.getPassword(), user.getEmail(), user.getPhoneNumber(), user.getAddress());
				userData.put(user.getUserId(), updatedUser);
				return true;
			} else {
				throw new NoSuchElementException("User not found with ID: " + user.getUserId());
			}
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			if (userData.containsKey(userId)) {
				userData.remove(userId);
				return true;
			} else {
				throw new NoSuchElementException("User not found with ID: " + userId);
			}
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public UserPojo getUserByUsername(String username) {
		try {
			UserPojo user = userData.values().stream().filter(u -> u.getUsername().equals(username)).findFirst()
					.orElse(null);
			if (user == null) {
				throw new NoSuchElementException("User not found with username: " + username);
			}
			return user;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

}
