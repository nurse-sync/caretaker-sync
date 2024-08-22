package app;

import service.UserService;
import service.CaretakerService;
import service.AdminService;
import pojo.UserPojo;
import pojo.CaretakerPojo;
import pojo.AdminPojo;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class DummyDataInitializer {

    private UserService userService;
    private CaretakerService caretakerService;
    private AdminService adminService;

    public DummyDataInitializer(UserService userService, CaretakerService caretakerService, AdminService adminService) {
        this.userService = userService;
        this.caretakerService = caretakerService;
        this.adminService = adminService;
    }

    public void initializeData() {
        initializeUsers();
        initializeCaretakers();
        initializeAdmins();
    }

    private void initializeUsers() {
        List<UserPojo> users = Arrays.asList(
            new UserPojo(1, "Alice", "alice", "password", "alice@example.com", "123 Main St", "1234567890"),
            new UserPojo(2, "Bob", "bob456", "password", "bob@example.com", "456 Maple St", "0987654321")
        );

        for (UserPojo user : users) {
            userService.createUser(user);
        }
    }

    private void initializeCaretakers() {
        List<CaretakerPojo> caretakers = Arrays.asList(
        		
        new CaretakerPojo(1, "John", "john", "password", "Male", "Nurse", 500.0, 
                         new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 30),
                         "City Hospital", "9876543210", "B.Sc Nursing", false, "Available"),
        
        new CaretakerPojo(2, "Mary", "mary", "password", "Female", "Caregiver", 400.0, 
                         new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 86400000L * 30),
                         "Green Valley", "1234567890", "Diploma in Caregiving", true, "Available")
        );

        for (CaretakerPojo caretaker : caretakers) {
            caretakerService.createCaretaker(caretaker);
        }
    }

    private void initializeAdmins() {
        List<AdminPojo> admins = Arrays.asList(
            new AdminPojo(1, "admin1", "admin1", "admin1@example.com", "1234567890", "Super Admin"),
            new AdminPojo(2, "admin2", "admin2", "admin2@example.com", "0987654321", "Admin")
            // Add more admins as needed
        );

        for (AdminPojo admin : admins) {
            adminService.createAdmin(admin);
        }
    }
}
