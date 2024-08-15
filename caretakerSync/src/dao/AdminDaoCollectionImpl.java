package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.AdminPojo;

public class AdminDaoCollectionImpl implements AdminDao{
    private Map<Integer, AdminPojo> adminData = new HashMap<>(); // HashMap to store admin data

    
	@Override
	public boolean addAdmin(AdminPojo admin) {
		int newId = adminData.size() + 1; // Generate a new ID based on the current size of the HashMap
        AdminPojo newAdmin = new AdminPojo(newId, admin.getUsername(), admin.getPassword(), admin.getEmail(), admin.getPhoneNumber(), admin.getRole());
        adminData.put(newId, newAdmin); // Add the admin to the HashMap
        return true; // Indicate success
	}

	@Override
	public AdminPojo getAdminById(int adminId) {
		return adminData.get(adminId); // Retrieve the admin by ID
	}

	@Override
	public List<AdminPojo> getAllAdmins() {
        return new ArrayList<>(adminData.values()); // Return a list of all admins
	}

	@Override
	public boolean updateAdmin(AdminPojo admin) {
		 if (adminData.containsKey(admin.getAdminId())) {
            AdminPojo updatedAdmin = new AdminPojo(admin.getAdminId(), admin.getUsername(), admin.getPassword(), admin.getEmail(), admin.getPhoneNumber(), admin.getRole());
            adminData.put(admin.getAdminId(), updatedAdmin); // Update the admin in the HashMap
            return true; // Indicate success
	     } else {
	         return false; // Indicate failure
	     }
	}

	@Override
	public boolean deleteAdmin(int adminId) {
		if (adminData.containsKey(adminId)) {
            adminData.remove(adminId); // Remove the admin from the HashMap
            return true; // Indicate success
        } else {
            return false; // Indicate failure
        }
	}

	@Override
	public AdminPojo getAdminByUsername(String username) {
		 return adminData.values().stream()
                 .filter(admin -> admin.getUsername().equals(username))
                 .findFirst()
                 .orElse(null);
	}
	
}
