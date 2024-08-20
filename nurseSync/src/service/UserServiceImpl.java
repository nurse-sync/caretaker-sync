package service;

import java.util.List;

import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.RequestPojo;
import pojo.UserPojo;
import dao.CaretakerDao;
import dao.UserDao;
import dao.RequestDao;


public class UserServiceImpl implements UserService{
	private UserDao userDao;
	private CaretakerDao caretakerDao;
	private RequestDao requestDao;

	
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

	@Override
	public List<CaretakerPojo> findMatchingCaretakers(CaretakerPreferences preferences) {
        return caretakerDao.findCaretakersByPreferences(preferences);

	}

	@Override
	public boolean sendRequestToCaretaker(int userId, int caretakerId) {
		if (caretakerDao.getCaretakerById(caretakerId) != null) {
            RequestPojo request = new RequestPojo(userId, caretakerId, "Pending");
            return requestDao.addRequest(request);
        }
        return false;
	}
	
}
