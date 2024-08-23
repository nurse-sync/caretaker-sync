package service;

import java.util.List;

import dao.AdminDao;
import pojo.AdminPojo;

public class AdminServiceImpl implements AdminService {
	private AdminDao adminDao;

	public AdminServiceImpl(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public boolean createAdmin(AdminPojo admin) {
		return adminDao.addAdmin(admin);

	}

	@Override
	public AdminPojo getAdminById(int adminId) {
		return adminDao.getAdminById(adminId);

	}

	@Override
	public List<AdminPojo> getAllAdmins() {
		return adminDao.getAllAdmins();

	}

	@Override
	public boolean updateAdmin(AdminPojo admin) {
		return adminDao.updateAdmin(admin);

	}

	@Override
	public boolean deleteAdmin(int adminId) {
		return adminDao.deleteAdmin(adminId);

	}

	@Override
	public AdminPojo getAdminByUsername(String username) {
		return adminDao.getAdminByUsername(username);
	}

}
