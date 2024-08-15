package service;

import java.util.List;

import pojo.AdminPojo;

public interface AdminService {
	boolean createAdmin(AdminPojo admin);
    AdminPojo getAdminById(int adminId);
    AdminPojo getAdminByUsername(String username);
    List<AdminPojo> getAllAdmins();
    boolean updateAdmin(AdminPojo admin);
    boolean deleteAdmin(int adminId);
}
