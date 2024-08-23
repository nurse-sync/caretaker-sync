package service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.RequestPojo;
import pojo.UserPojo;
import dao.CaretakerDao;
import dao.UserDao;
import exceptions.GlobalExceptionHandler;
import dao.RequestDao;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private CaretakerDao caretakerDao;
	private RequestDao requestDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean createUser(UserPojo user) {
		try {
			return userDao.addUser(user);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // Indicate failure
		}
	}

	@Override
	public UserPojo getUserById(int userId) {
		try {
			UserPojo user = userDao.getUserById(userId);
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
			return userDao.getAllUsers();
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list
		}
	}

	@Override
	public boolean updateUser(UserPojo user) {
		try {
			return userDao.updateUser(user);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		try {
			return userDao.deleteUser(userId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // Indicate failure
		}
	}

	@Override
	public UserPojo getUserByUsername(String username) {
		try {
			UserPojo user = userDao.getUserByUsername(username);
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

	@Override
	public List<CaretakerPojo> findMatchingCaretakers(CaretakerPreferences preferences) {
		try {
			if (preferences == null) {
				throw new IllegalArgumentException("Caretaker preferences cannot be null");
			}
			return caretakerDao.findCaretakersByPreferences(preferences);
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleIllegalArgumentException(e);
			return new ArrayList<>(); // Return an empty list
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list
		}
	}

	@Override
	public boolean sendRequestToCaretaker(int userId, int caretakerId, String serviceLocation, String patientName,
			int patientAge, String patientGender, Date startDate, Date endDate) {
		try {
			if (caretakerDao.getCaretakerById(caretakerId) != null) {
				int newRequestId = generateNewRequestId();

				RequestPojo request = new RequestPojo(newRequestId, userId, caretakerId, "Pending", serviceLocation,
						patientName, patientAge, patientGender, startDate, endDate);
				return requestDao.addRequest(request);
			}
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	private int generateNewRequestId() {
		try {
			int maxRequestId = requestDao.getMaxRequestId();
			return maxRequestId + 1;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return -1; // failure
		}
	}
}
