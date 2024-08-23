package service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import dao.AdminDao;
import exceptions.GlobalExceptionHandler;
import pojo.AdminPojo;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean createAdmin(AdminPojo admin) {
		try {
			return adminDao.addAdmin(admin);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public AdminPojo getAdminById(int adminId) {
		try {
			AdminPojo admin = adminDao.getAdminById(adminId);
			if (admin == null) {
				throw new NoSuchElementException("Admin not found with ID: " + adminId);
			}
			return admin;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

	@Override
	public List<AdminPojo> getAllAdmins() {
		try {
			return adminDao.getAllAdmins();
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>(); // Return an empty list;
		}
	}

	@Override
	public boolean updateAdmin(AdminPojo admin) {
		try {
			return adminDao.updateAdmin(admin);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public boolean deleteAdmin(int adminId) {
		try {
			return adminDao.deleteAdmin(adminId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false; // failure
		}
	}

	@Override
	public AdminPojo getAdminByUsername(String username) {
		try {
			AdminPojo admin = adminDao.getAdminByUsername(username);
			if (admin == null) {
				throw new NoSuchElementException("Admin not found with username: " + username);
			}
			return admin;
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}

	}

}
