package presentation;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import pojo.AdminPojo;
import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.RequestPojo;
import pojo.UserPojo;
import service.AdminService;
import service.CaretakerService;
import service.UserService;
import validators.InputValidator;
import dao.RequestDao;
import exceptions.InvalidInputException;

public class Presentation {

	private UserService userService;
    private CaretakerService caretakerService;
    private AdminService adminService;
//    private RequestDao requestDao;
    private Scanner scanner;
    
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    
    // Constructor to initialize services and scanner
    public Presentation(UserService userService, CaretakerService caretakerService, AdminService adminService) {
        this.userService = userService;
        this.caretakerService = caretakerService;
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }
    
    // method to start the presentation layer
    public void start() {
        while (true) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	userMenu();
                    break;
                    
                case 2:
                    caretakerMenu();
                    break;
                    
                case 3:
                    adminMenu();
                    break;
                    
                case 4:
                    System.out.println("Exiting the application...");
                    return;
                    
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private void displayMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. User");
        System.out.println("2. Caretaker");
        System.out.println("3. Admin");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void userMenu() {
        System.out.println("\nUser Menu:");
        System.out.println("1. Sign In/Sign Up?");
        System.out.println("2. View all Nurse/Caretaker");
        System.out.println("3. Exit");
        
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
            	userSigninSignupMenu();
                break;
                
            case 2: 
                displayAllCaretakers();
                break;
                
            case 3:
                return;
                
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    
    private void userSigninSignupMenu() {
    	System.out.println("Hire your nurse now!");
        System.out.println("1. Existing User? Sign In");
        System.out.println("2. New user? Then Sign Up");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
            	userLogin();
                break;
            case 2: 
            	createUserAccount();
            	break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    
    private void userLogin() {
        System.out.print("Enter User Username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter User Password: ");
        String password = scanner.nextLine();

        UserPojo user = userService.getUserByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            handleUserOperations();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    
    private void userOperationsAfterLogin() {
    	System.out.println("Welcome to Nurse Sync!");
        System.out.println("1. Search with Preference?");
        System.out.println("2. List all the Nurse/Caregiver");
        System.out.print("Enter your choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
            	provideCaretakerPreferences();
                break;
                
            case 2: 
            	displayAllCaretakers();
            	break;
            	
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    
    private void createUserAccount() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        
        // Create UserPojo with a single CaretakerPreferences object
        UserPojo user = new UserPojo(0, name, username, password, email, address, phoneNumber);
        boolean success = userService.createUser(user);
        
        if (success) {
            System.out.println("User account created successfully!");
            handleUserOperations();
        } else {
            System.out.println("User account creation failed!");
        }
    }
    
   
    
    private void provideCaretakerPreferences(CaretakerPreferences preferences) {
    	
    	// Get the category using the InputValidator
        String category = InputValidator.validateCategory(scanner);
        preferences.setCategory(category);
        
        // Get the gender preference using the InputValidator
        String genderPreference = InputValidator.validateGenderPreference(scanner);
        preferences.setGenderPreference(genderPreference);
        

        // Get the max weekly rate using the InputValidator
        double maxWeeklyRate = InputValidator.validateMaxWeeklyRate(scanner);
        preferences.setMaxWeeklyRate(maxWeeklyRate);

        
        boolean validInput = false;
        
        while (!validInput) {
            try {
                // Get and validate Required From date
                Date requiredFrom = InputValidator.promptAndValidateDate(scanner, "Enter new Required From (yyyy-MM-dd): ");
                
                // Get and validate Required To date
                Date requiredTo = InputValidator.promptAndValidateDate(scanner, "Enter new Required To (yyyy-MM-dd): ");
                
                // Validate date order
                InputValidator.validateDateOrder(requiredFrom, requiredTo);

                // Set the preferences
                preferences.setRequiredFrom(requiredFrom);
                preferences.setRequiredTo(requiredTo);
                
                // Exit loop if everything is valid
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                System.out.println("Please try again.");
            }
        }
        
        // Validate and get the service location
        String serviceLocation = InputValidator.validateServiceLocation(scanner);
        preferences.setServiceLocation(serviceLocation);
        
        // Validate and get the live-in requirement
        boolean liveIn = InputValidator.validateLiveIn(scanner);
        preferences.setLiveIn(liveIn);
        
        // Get the value for "For Whom is the Service?" using the InputValidator
        String forWhom = InputValidator.validateForWhom(scanner);
        preferences.setForWhom(forWhom);
    }
    
    private void updateUserAccount() {
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your new name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter your new username: ");
        String username = scanner.nextLine();
        
        System.out.print("Enter your new password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter your new email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter your new phone number: ");
        String phoneNumber = scanner.nextLine();
        
        System.out.print("Enter your new address: ");
        String address = scanner.nextLine();
        
        // Get caretaker preferences from the user
        System.out.print("Enter caretaker category (e.g., Nurse, Caretaker, NurseCumCaretaker): ");
        String category = scanner.nextLine();
        
        System.out.print("Enter gender preference (e.g., Male, Female, Any): ");
        String genderPreference = scanner.nextLine();
        
        System.out.print("Enter maximum weekly rate: ");
        double maxWeeklyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter required from date and time (yyyy-MM-ddTHH:mm): ");
        LocalDateTime requiredFrom = LocalDateTime.parse(scanner.nextLine());
        
        System.out.print("Enter required to date and time (yyyy-MM-ddTHH:mm): ");
        LocalDateTime requiredTo = LocalDateTime.parse(scanner.nextLine());
        
        System.out.print("Enter service location: ");
        String serviceLocation = scanner.nextLine();
        
        System.out.print("Is live-in required? (true/false): ");
        boolean liveIn = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter for whom the service is needed (e.g., Self, Mother, Father, Friend): ");
        String forWhom = scanner.nextLine();
        
        CaretakerPreferences caretakerPreferences = new CaretakerPreferences(category, genderPreference, maxWeeklyRate, 
                requiredFrom, requiredTo, serviceLocation, liveIn, forWhom);

        UserPojo user = new UserPojo(userId, name, username, password, email, phoneNumber, address, caretakerPreferences);
        boolean success = userService.updateUser(user);
        
        if (success) {
            System.out.println("User account updated successfully!");
        } else {
            System.out.println("User not found. Update failed.");
        }
    }
    
    private void updateUserField() {
        System.out.print("Enter the User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Retrieve the user by ID
        UserPojo user = userService.getUserById(userId);
        
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("\nSelect the field to update:");
        System.out.println("1. Name");
        System.out.println("2. Username");
        System.out.println("3. Password");
        System.out.println("4. Email");
        System.out.println("5. Phone Number");
        System.out.println("6. Address");
        System.out.println("7. Caretaker Preferences");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter new Name: ");
                String newName = scanner.nextLine();
                user.setName(newName);
                break;
            case 2:
                System.out.print("Enter new Username: ");
                String newUsername = scanner.nextLine();
                user.setUsername(newUsername);
                break;
            case 3:
                System.out.print("Enter new Password: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword);
                break;
            case 4:
                System.out.print("Enter new Email: ");
                String newEmail = scanner.nextLine();
                user.setEmail(newEmail);
                break;
            case 5:
                System.out.print("Enter new Phone Number: ");
                String newPhoneNumber = scanner.nextLine();
                user.setPhoneNumber(newPhoneNumber);
                break;
            case 6:
                System.out.print("Enter new Address: ");
                String newAddress = scanner.nextLine();
                user.setAddress(newAddress);
                break;
            case 7:
                System.out.println("Updating Caretaker Preferences:");
                updateCaretakerPreferences(user.getCaretakerPreferences());
                break;
            default:
                System.out.println("Invalid choice. No changes made.");
                return;
        }

        // Update the user in the data store
        boolean success = userService.updateUser(user);
        if (success) {
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Failed to update user.");
        }
    }

    private void updateCaretakerPreferences(CaretakerPreferences preferences) {
        System.out.print("Enter new Category (e.g., Nurse, Caretaker): ");
        String category = scanner.nextLine();
        System.out.print("Enter new Gender Preference (Male, Female, Any): ");
        String genderPreference = scanner.nextLine();
        System.out.print("Enter new Max Weekly Rate: ");
        double maxWeeklyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new Required From (yyyy-MM-ddTHH:mm): ");
        String from = scanner.nextLine();
        System.out.print("Enter new Required To (yyyy-MM-ddTHH:mm): ");
        String to = scanner.nextLine();
        LocalDateTime requiredFrom = LocalDateTime.parse(from);
        LocalDateTime requiredTo = LocalDateTime.parse(to);
        System.out.print("Enter new Service Location: ");
        String serviceLocation = scanner.nextLine();
        System.out.print("Is Live-In Required? (true/false): ");
        boolean liveIn = scanner.nextBoolean();
        scanner.nextLine(); // Consume newline
        System.out.print("For Whom is the Service? (e.g., Mother, Self): ");
        String forWhom = scanner.nextLine();

        // Update the caretaker preferences
        preferences.setCategory(category);
        preferences.setGenderPreference(genderPreference);
        preferences.setMaxWeeklyRate(maxWeeklyRate);
        preferences.setRequiredFrom(requiredFrom);
        preferences.setRequiredTo(requiredTo);
        preferences.setServiceLocation(serviceLocation);
        preferences.setLiveIn(liveIn);
        preferences.setForWhom(forWhom);
    }
    
    private void deleteUserAccount() {
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        boolean success = userService.deleteUser(userId);
        
        if (success) {
            System.out.println("User account deleted successfully!");
        } else {
            System.out.println("User not found. Deletion failed.");
        }
    }
    
    private void viewUserAccount() {
        System.out.print("Enter your user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        UserPojo user = userService.getUserById(userId);
        
        if (user != null) {
            System.out.println("User Details: " + user.toString());
        } else {
            System.out.println("User not found.");
        }
    }
    
    private void displayAllUsers() {
        System.out.println("\nList of All Users:");

        // Call the service method to get all users
        List<UserPojo> users = userService.getAllUsers();

        // Check if the list is empty
        if (users.isEmpty()) {
            System.out.println("No users found.");
        } else {
            // Loop through the list of users and display their details
            for (UserPojo user : users) {
                System.out.println("\nUser ID: " + user.getUserId());
                System.out.println("Name: " + user.getName());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Email: " + user.getEmail());
                System.out.println("Phone Number: " + user.getPhoneNumber());
                System.out.println("Address: " + user.getAddress());
                
                // Display caretaker preferences if available
                CaretakerPreferences preferences = user.getCaretakerPreferences();
                if (preferences != null) {
                    System.out.println("Caretaker Preferences:");
                    System.out.println("  - Category: " + preferences.getCategory());
                    System.out.println("  - Gender Preference: " + preferences.getGenderPreference());
                    System.out.println("  - Max Weekly Rate: " + preferences.getMaxWeeklyRate());
                    System.out.println("  - Required From: " + preferences.getRequiredFrom());
                    System.out.println("  - Required To: " + preferences.getRequiredTo());
                    System.out.println("  - Service Location: " + preferences.getServiceLocation());
                    System.out.println("  - Live-In: " + preferences.isLiveIn());
                    System.out.println("  - For Whom: " + preferences.getForWhom());
                } else {
                    System.out.println("Caretaker Preferences: Not Set");
                }
            }
        }
    }

    
    private void handleCaretakerOperations() {
        System.out.println("\nCaretaker Operations:");
        System.out.println("1. Create Caretaker Account");
        System.out.println("2. Login to my Caretaker Account");
        System.out.println("3. Update Caretaker Account");
        System.out.println("4. Delete Caretaker Account");
        System.out.println("5. View Caretaker Account");
        System.out.println("6. View All Caretakers");
        System.out.println("7. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createCaretakerAccount();
                break;
                
            case 2:      
            	caretakerLogin();
                break;
                
            case 3:
                updateCaretakerAccount();
                break;
                
            case 4:
                deleteCaretakerAccount();
                break;
                
            case 5:
                viewCaretakerAccount();
                break;
                
            case 6:
            	displayAllCaretakers();
                break;
            
            case 7:
                return;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    
    private void displayAllCaretakers() {
        System.out.println("\nList of All Caretakers:");

        // Call the service method to get all caretakers
        List<CaretakerPojo> caretakers = caretakerService.getAllCaretakers();

        // Check if the list is empty
        if (caretakers.isEmpty()) {
            System.out.println("No caretakers found.");
        } else {
            // Loop through the list of caretakers and display their details
            for (CaretakerPojo caretaker : caretakers) {
                System.out.println("\nCaretaker ID: " + caretaker.getCaretakerId());
                System.out.println("Name: " + caretaker.getName());
                System.out.println("Gender: " + caretaker.getGender());
                System.out.println("Category: " + caretaker.getCategory());
                System.out.println("Weekly Rate: " + caretaker.getWeeklyRate());
                System.out.println("Availability From: " + caretaker.getAvailabilityFrom());
                System.out.println("Availability To: " + caretaker.getAvailabilityTo());
                System.out.println("Location: " + caretaker.getLocation());
                System.out.println("Phone Number: " + caretaker.getPhoneNumber());
                System.out.println("Qualifications: " + caretaker.getQualifications());
                System.out.println("Live-In: " + (caretaker.getIsLiveIn() ? "Yes" : "No"));
                System.out.println("Status: " + caretaker.getStatus());
            }
        }
    }

    private void createCaretakerAccount() {
        System.out.println("Create Caretaker Account");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter gender (Male, Female, Others): ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter category (e.g., Nurse, Caretaker): ");
        String category = scanner.nextLine();
        
        System.out.print("Enter weekly rate: ");
        double weeklyRate = scanner.nextDouble();
        
        System.out.print("Enter availability start date and time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime availabilityFrom = LocalDateTime.parse(scanner.next());
        
        System.out.print("Enter availability end date and time (yyyy-MM-ddTHH:mm:ss): ");
        LocalDateTime availabilityTo = LocalDateTime.parse(scanner.next());
        
        scanner.nextLine(); // Consume newline
        
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        System.out.print("Enter qualifications: ");
        String qualifications = scanner.nextLine();
        
        System.out.print("Are you willing to live in? (true/false): ");
        boolean isLiveIn = scanner.nextBoolean();
        
        String status;
        LocalDateTime currentDateTime = LocalDateTime.now();
        
        if (currentDateTime.isAfter(availabilityFrom) && currentDateTime.isBefore(availabilityTo)) {
            status = "Available";
        } else if (currentDateTime.isBefore(availabilityFrom)) {
            status = "Not Yet Available";
        } else {
            status = "Unavailable";
        }
        
        CaretakerPojo caretaker = new CaretakerPojo(0, name, password, gender, category, weeklyRate, availabilityFrom, availabilityTo, 
                location, phoneNumber, qualifications, isLiveIn, status);
        
        boolean success = caretakerService.createCaretaker(caretaker);
        
        if (success) {
            System.out.println("Caretaker account created successfully.");
        } else {
            System.out.println("Failed to create caretaker account.");
        }
    }
    
    private void caretakerLogin() {
        System.out.print("Enter Caretaker Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Caretaker Password: ");
        String password = scanner.nextLine();

        CaretakerPojo caretaker = caretakerService.getCaretakerByUsername(username);
        if (caretaker != null && caretaker.getPassword().equals(password)) {
            System.out.println("Login successful!");
            handleCaretakerOperations();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    
    private void updateCaretakerAccount() {
        System.out.println("Update Caretaker Account");

        System.out.print("Enter Caretaker ID to update: ");
        int caretakerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CaretakerPojo existingCaretaker = caretakerService.getCaretakerById(caretakerId);

        if (existingCaretaker != null) {
            System.out.print("Enter new name (current: " + existingCaretaker.getName() + "): ");
            String name = scanner.nextLine();
            
            System.out.print("Enter new password (current: " + existingCaretaker.getPassword() + "): ");
            String password = scanner.nextLine();
            
            System.out.print("Enter new gender (current: " + existingCaretaker.getGender() + "): ");
            String gender = scanner.nextLine();
            
            System.out.print("Enter new category (current: " + existingCaretaker.getCategory() + "): ");
            String category = scanner.nextLine();
            
            System.out.print("Enter new weekly rate (current: " + existingCaretaker.getWeeklyRate() + "): ");
            double weeklyRate = scanner.nextDouble();
            
            System.out.print("Enter new availability start date and time (current: " + existingCaretaker.getAvailabilityFrom() + ", format: yyyy-MM-ddTHH:mm:ss): ");
            LocalDateTime availabilityFrom = LocalDateTime.parse(scanner.next());
            
            System.out.print("Enter new availability end date and time (current: " + existingCaretaker.getAvailabilityTo() + ", format: yyyy-MM-ddTHH:mm:ss): ");
            LocalDateTime availabilityTo = LocalDateTime.parse(scanner.next());

            scanner.nextLine(); // Consume newline

            System.out.print("Enter new location (current: " + existingCaretaker.getLocation() + "): ");
            String location = scanner.nextLine();
            
            System.out.print("Enter new phone number (current: " + existingCaretaker.getPhoneNumber() + "): ");
            String phoneNumber = scanner.nextLine();
            
            System.out.print("Enter new qualifications (current: " + existingCaretaker.getQualifications() + "): ");
            String qualifications = scanner.nextLine();
            
            System.out.print("Are you willing to live in? (current: " + existingCaretaker.getIsLiveIn() + ", true/false): ");
            boolean isLiveIn = scanner.nextBoolean();

            String status;
            
            LocalDateTime currentDateTime = LocalDateTime.now();
            
            if (currentDateTime.isAfter(availabilityFrom) && currentDateTime.isBefore(availabilityTo)) {
                status = "Available";
            } else if (currentDateTime.isBefore(availabilityFrom)) {
                status = "Not Yet Available";
            } else {
                status = "Unavailable";
            }
            
            CaretakerPojo updatedCaretaker = new CaretakerPojo(caretakerId, name, password, gender, category, weeklyRate, 
                    availabilityFrom, availabilityTo, location, phoneNumber, qualifications, isLiveIn, status);
            
            boolean success = caretakerService.updateCaretaker(updatedCaretaker);
            
            if (success) {
                System.out.println("Caretaker account updated successfully.");
            } else {
                System.out.println("Failed to update caretaker account.");
            }
        } else {
            System.out.println("Caretaker with ID " + caretakerId + " not found.");
        }
    }
    
    private void deleteCaretakerAccount() {
        System.out.println("Delete Caretaker Account");

        System.out.print("Enter Caretaker ID to delete: ");
        int caretakerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = caretakerService.deleteCaretaker(caretakerId);
        
        if (success) {
            System.out.println("Caretaker account deleted successfully.");
        } else {
            System.out.println("Failed to delete caretaker account. Caretaker ID not found.");
        }
    }

    private void viewCaretakerAccount() {
        System.out.println("View Caretaker Account");

        System.out.print("Enter Caretaker ID to view: ");
        int caretakerId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        CaretakerPojo caretaker = caretakerService.getCaretakerById(caretakerId);
        
        if (caretaker != null) {
            System.out.println("Caretaker Details:");
            System.out.println("Name: " + caretaker.getName());
            System.out.println("Gender: " + caretaker.getGender());
            System.out.println("Category: " + caretaker.getCategory());
            System.out.println("Weekly Rate: " + caretaker.getWeeklyRate());
            System.out.println("Availability From: " + caretaker.getAvailabilityFrom());
            System.out.println("Availability To: " + caretaker.getAvailabilityTo());
            System.out.println("Location: " + caretaker.getLocation());
            System.out.println("Phone Number: " + caretaker.getPhoneNumber());
            System.out.println("Qualifications: " + caretaker.getQualifications());
            System.out.println("Live-In: " + caretaker.getIsLiveIn());
        } else {
            System.out.println("Caretaker with ID " + caretakerId + " not found.");
        }
    }
    
    private void handleAdminOperations() {
        System.out.println("\nAdmin Operations:");
        System.out.println("1. Create Admin Account");
        System.out.println("2. Login to my Admin Account");
        System.out.println("3. Update Admin Account");
        System.out.println("4. Delete Admin Account");
        System.out.println("5. View Admin Account");
        System.out.println("5. View All Admin Accounts");
        System.out.println("6. Back to Main Menu");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                createAdminAccount();
                break;
                
            case 2:
            	adminLogin();
                break;   
                
            case 3:
                updateAdminAccount();
                break;
                
            case 4:
                deleteAdminAccount();
                break;
                
            case 5:
                viewAdminAccount();
                break;
                
            case 6:
            	displayAllAdmins();
                break;
                
            case 7:
                return; // Go back to the main menu
                
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                handleAdminOperations();
                break;
        }
    }

    private void displayAllAdmins() {
        System.out.println("\nList of All Admins:");

        // Call the service method to get all admins
        List<AdminPojo> admins = adminService.getAllAdmins();

        // Check if the list is empty
        if (admins.isEmpty()) {
            System.out.println("No admins found.");
        } else {
            // Loop through the list of admins and display their details
            for (AdminPojo admin : admins) {
                System.out.println("\nAdmin ID: " + admin.getAdminId());
                System.out.println("Username: " + admin.getUsername());
                System.out.println("Email: " + admin.getEmail());
                System.out.println("Phone Number: " + admin.getPhoneNumber());
                System.out.println("Role: " + admin.getRole());
                
            }
        }
    }

    
    private void createAdminAccount() {
        System.out.println("Create Admin Account");

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        
        System.out.print("Enter role (e.g., Admin, Super Admin): ");
        String role = scanner.nextLine();

        // Generate a new ID based on the current size of the HashMap or another ID generation strategy
        int newId = adminService.getAllAdmins().size() + 1;

        AdminPojo admin = new AdminPojo(newId, username, password, email, phoneNumber, role);

        boolean success = adminService.createAdmin(admin);

        if (success) {
            System.out.println("Admin account created successfully.");
        } else {
            System.out.println("Failed to create admin account.");
        }
    }

    private void adminLogin() {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        AdminPojo admin = adminService.getAdminByUsername(username);
        if (admin != null && admin.getPassword().equals(password)) {
            System.out.println("Login successful!");
            handleAdminOperations();
        } else {
            System.out.println("Invalid credentials.");
        }
    }
    
    private void updateAdminAccount() {
        System.out.println("Update Admin Account");

        System.out.print("Enter Admin ID to update: ");
        int adminId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        AdminPojo existingAdmin = adminService.getAdminById(adminId);

        if (existingAdmin != null) {
            System.out.print("Enter new username (current: " + existingAdmin.getUsername() + "): ");
            String username = scanner.nextLine();
            
            System.out.print("Enter new password (current: " + existingAdmin.getPassword() + "): ");
            String password = scanner.nextLine();
            
            System.out.print("Enter new email (current: " + existingAdmin.getEmail() + "): ");
            String email = scanner.nextLine();
            
            System.out.print("Enter new phone number (current: " + existingAdmin.getPhoneNumber() + "): Admin or Super Admin"
            		+ " ");
            String phoneNumber = scanner.nextLine();
            
            System.out.print("Enter new role (current: " + existingAdmin.getRole() + "): ");
            String role = scanner.nextLine();

            AdminPojo updatedAdmin = new AdminPojo(adminId, username, password, email, phoneNumber, role);
            
            boolean success = adminService.updateAdmin(updatedAdmin);
            
            if (success) {
                System.out.println("Admin account updated successfully.");
            } else {
                System.out.println("Failed to update admin account.");
            }
        } else {
            System.out.println("Admin with ID " + adminId + " not found.");
        }
    }

    private void deleteAdminAccount() {
        System.out.println("Delete Admin Account");

        System.out.print("Enter Admin ID to delete: ");
        int adminId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        boolean success = adminService.deleteAdmin(adminId);
        
        if (success) {
            System.out.println("Admin account deleted successfully.");
        } else {
            System.out.println("Failed to delete admin account. Admin ID not found.");
        }
    }

    private void viewAdminAccount() {
        System.out.println("View Admin Account");

        System.out.print("Enter Admin ID to view: ");
        int adminId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        AdminPojo admin = adminService.getAdminById(adminId);
        
        if (admin != null) {
            System.out.println("Admin Details:");
            System.out.println("ID: " + admin.getAdminId());
            System.out.println("Username: " + admin.getUsername());
            System.out.println("Email: " + admin.getEmail());
            System.out.println("Phone Number: " + admin.getPhoneNumber());
            System.out.println("Role: " + admin.getRole());
        } else {
            System.out.println("Admin with ID " + adminId + " not found.");
        }
    }

//    UserPojo user = userService.getUserById(1); 
//    CaretakerPreferences preferences = user.getCaretakerPreferences();
//    List<CaretakerPojo> matchingCaretakers = caretakerService..findCaretakersByPreferences(preferences);

    
//    private void findMatchingCaretakers() {
//        // Collect user preferences
//        CaretakerPreferences preferences = collectPreferences();
//        List<CaretakerPojo> caretakers = userService.findMatchingCaretakers(preferences);
//
//        if (caretakers.isEmpty()) {
//            System.out.println("No matching caretakers found.");
//        } else {
//            for (int i = 0; i < caretakers.size(); i++) {
//                CaretakerPojo caretaker = caretakers.get(i);
//                System.out.println((i + 1) + ". " + caretaker.getName() + " - " + caretaker.getCategory() +
//                    " (Rate: " + caretaker.getWeeklyRate() + ")");
//            }
//        }
//    }
//
//    private void sendRequestToCaretaker() {
//        System.out.print("Enter your user ID: ");
//        int userId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.print("Enter caretaker ID to send request: ");
//        int caretakerId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        boolean success = userService.sendRequestToCaretaker(userId, caretakerId);
//        if (success) {
//            System.out.println("Request sent successfully.");
//        } else {
//            System.out.println("Failed to send request.");
//        }
//    }
//
//    private void viewRequests() {
//        // Assuming caretakerId is available
//        int caretakerId = 1; // Replace with actual caretaker ID
//        List<RequestPojo> requests = requestDao.getAllRequestsByCaretakerId(caretakerId);
//
//        if (requests.isEmpty()) {
//            System.out.println("No requests found.");
//        } else {
//            for (RequestPojo request : requests) {
//                System.out.println("Request ID: " + request.getRequestId() +
//                    ", User ID: " + request.getUserId() +
//                    ", Status: " + request.getStatus());
//            }
//        }
//    }
//
//    private void handleRequest() {
//        System.out.print("Enter request ID: ");
//        int requestId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.print("Accept or reject request? (a/r): ");
//        String response = scanner.nextLine();
//
//        boolean accept = response.equalsIgnoreCase("a");
//        boolean success = caretakerService.handleRequest(requestId, accept);
//
//        if (success) {
//            System.out.println("Request handled successfully.");
//        } else {
//            System.out.println("Failed to handle request.");
//        }
//    }
//    
    
    

//    private void updateCaretakerStatus() {
//        System.out.print("Enter Caretaker ID: ");
//        int caretakerId = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        System.out.print("Enter new status (Available/Booked): ");
//        String status = scanner.nextLine();
//
//        boolean success = caretakerService.updateCaretakerStatus(caretakerId, status);
//        if (success) {
//            System.out.println("Caretaker status updated successfully.");
//        } else {
//            System.out.println("Caretaker not found. Status update failed.");
//        }
//    }

//    public void handleLogin() {
//        System.out.println("Login Options:");
//        System.out.println("1. Admin Login");
//        System.out.println("2. Caretaker Login");
//        System.out.println("3. User Login");
//        System.out.print("Enter your choice: ");
//
//        int choice = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        switch (choice) {
//            case 1:
//                adminLogin();
//                break;
//            case 2:
//                caretakerLogin();
//                break;
//            case 3:
//                userLogin();
//                break;
//            default:
//                System.out.println("Invalid choice. Please try again.");
//        }
//    }


}

