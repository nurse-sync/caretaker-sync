package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.GlobalExceptionHandler;
import pojo.AdminPojo;

public class AdminDaoCollectionImpl implements AdminDao {
	private Map<Integer, AdminPojo> adminData = new HashMap<>();

	@Override
	public boolean addAdmin(AdminPojo admin) {
		try {
			if (admin == null || admin.getUsername() == null || admin.getPassword() == null) {
				throw new IllegalArgumentException("Invalid admin details provided.");
			}
			int newId = adminData.size() + 1;
			AdminPojo newAdmin = new AdminPojo(newId, admin.getUsername(), admin.getPassword(), admin.getEmail(),
					admin.getPhoneNumber(), admin.getRole());
			adminData.put(newId, newAdmin);
			return true;
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput();
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public AdminPojo getAdminById(int adminId) {
		try {
			AdminPojo admin = adminData.get(adminId);
			if (admin == null) {
				throw new IllegalArgumentException("Admin with ID " + adminId + " not found.");
			}
			return admin;
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput();
			return null;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

	@Override
	public List<AdminPojo> getAllAdmins() {
		try {
			return new ArrayList<>(adminData.values());
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return new ArrayList<>();
		}
	}

	@Override
	public boolean updateAdmin(AdminPojo admin) {
		try {
			if (admin == null || !adminData.containsKey(admin.getAdminId())) {
				throw new IllegalArgumentException("Admin with ID " + admin.getAdminId() + " does not exist.");
			}
			AdminPojo updatedAdmin = new AdminPojo(admin.getAdminId(), admin.getUsername(), admin.getPassword(),
					admin.getEmail(), admin.getPhoneNumber(), admin.getRole());
			adminData.put(admin.getAdminId(), updatedAdmin);
			return true;
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput();
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public boolean deleteAdmin(int adminId) {
		try {
			if (adminData.containsKey(adminId)) {
				adminData.remove(adminId);
				return true;
			} else {
				throw new IllegalArgumentException("Admin with ID " + adminId + " does not exist.");
			}
		} catch (IllegalArgumentException e) {
			GlobalExceptionHandler.handleInvalidInput();
			return false;
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return false;
		}
	}

	@Override
	public AdminPojo getAdminByUsername(String username) {
		try {
			return adminData.values().stream().filter(admin -> admin.getUsername().equals(username)).findFirst()
					.orElse(null);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
			return null;
		}
	}

}
