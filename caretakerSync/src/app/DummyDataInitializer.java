package app;

import java.time.LocalDateTime;

import dao.AdminDaoCollectionImpl;
import dao.CaretakerDaoCollectionImpl;
import dao.UserDaoCollectionImpl;
import pojo.AdminPojo;
import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.UserPojo;
import service.AdminService;
import service.AdminServiceImpl;
import service.CaretakerService;
import service.CaretakerServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class DummyDataInitializer {
	
	    
	public static void initialize() {
		
		CaretakerService caretakerService = new CaretakerServiceImpl(new CaretakerDaoCollectionImpl()); 
		UserService userService = new UserServiceImpl(new UserDaoCollectionImpl()); 
		AdminService adminService = new AdminServiceImpl(new AdminDaoCollectionImpl()); 
		    
		CaretakerPreferences matchingPreferences = new CaretakerPreferences("Nurse", "Female", 5000.0, 
	            LocalDateTime.of(2024, 8, 20, 9, 0), LocalDateTime.of(2024, 8, 25, 17, 0),
	            "Chennai", true, "Mother");

        CaretakerPreferences nonMatchingPreferences = new CaretakerPreferences("Caretaker", "Male", 4000.0, 
            LocalDateTime.of(2024, 9, 1, 9, 0), LocalDateTime.of(2024, 9, 15, 18, 0),
            "Bangalore", false, "Father");

        UserPojo user1 = new UserPojo(1, "Ravi Kumar", "ravi_kumar", "password123", "ravi.kumar@example.com", 
            "9876543210", "Chennai", matchingPreferences);

        UserPojo user2 = new UserPojo(2, "Anjali Sharma", "anjali_sharma", "password456", "anjali.sharma@example.com", 
            "9876543211", "Bangalore", nonMatchingPreferences);

        userService.createUser(user1);
        userService.createUser(user2);

        // Admins
        AdminPojo admin1 = new AdminPojo(1, "admin1", "adminpass123", "admin1@example.com", "1234567890", "Admin");
        AdminPojo admin2 = new AdminPojo(2, "superadmin", "superpass456", "superadmin@example.com", "0987654321", "Super Admin");

        adminService.createAdmin(admin1);
        adminService.createAdmin(admin2);

        // Caretakers
        CaretakerPojo matchingCaretaker = new CaretakerPojo(1, "Maya Sen", "1234", "Female", "Nurse", 5000.0, 
            LocalDateTime.of(2024, 8, 20, 9, 0), LocalDateTime.of(2024, 8, 25, 17, 0), 
            "Chennai", "9876543212", "B.Sc Nursing", true, "Available");

        CaretakerPojo nonMatchingCaretaker = new CaretakerPojo(2, "Rajesh Verma", "12345", "Male", "Caretaker", 4000.0, 
            LocalDateTime.of(2024, 9, 1, 9, 0), LocalDateTime.of(2024, 9, 15, 18, 0), 
            "Bangalore", "9876543213", "Experienced Caregiver", false, "Available");

        CaretakerPojo anotherMatchingCaretaker = new CaretakerPojo(3, "Sita Devi", "qwer", "Female", "Nurse", 4500.0, 
            LocalDateTime.of(2024, 8, 20, 9, 0), LocalDateTime.of(2024, 8, 25, 17, 0), 
            "Chennai", "9876543214", "Certified Nurse", true, "Available");

        caretakerService.createCaretaker(matchingCaretaker);
        caretakerService.createCaretaker(nonMatchingCaretaker);
        caretakerService.createCaretaker(anotherMatchingCaretaker);
	    }
}