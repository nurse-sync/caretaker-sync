package dao;

import java.util.List;

import pojo.AdminPojo;

public interface AdminDao {
	boolean addAdmin(AdminPojo admin);
    AdminPojo getAdminById(int adminId);
    AdminPojo getAdminByUsername(String username);
    List<AdminPojo> getAllAdmins();
    boolean updateAdmin(AdminPojo admin);
    boolean deleteAdmin(int adminId);
}
