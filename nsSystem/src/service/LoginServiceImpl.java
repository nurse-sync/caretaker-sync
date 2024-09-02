package service;

import dao.LoginDao;
import dao.LoginDaoImpl;
import model.LoginPojo;

public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;

	public LoginServiceImpl() {
		this.loginDao = new LoginDaoImpl();
	}

	@Override
	public LoginPojo getUserById(int userId) {
		return loginDao.getUserById(userId);
	}

	@Override
	public LoginPojo getUserByUserName(String userName) {
		return loginDao.getUserByUserName(userName);
	}

	@Override
	public boolean addUser(LoginPojo user) {
		return loginDao.addUser(user);
	}

	@Override
	public boolean updateUser(LoginPojo user) {
		return loginDao.updateUser(user);
	}

	@Override
	public boolean deleteUser(int userId) {
		return loginDao.deleteUser(userId);
	}

}
