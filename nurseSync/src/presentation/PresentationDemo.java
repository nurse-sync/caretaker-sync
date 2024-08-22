package presentation;

import java.sql.Date;
import java.text.ParseException;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import pojo.AdminPojo;
import pojo.CaretakerPojo;
import pojo.CaretakerPreferences;
import pojo.RequestPojo;
import pojo.UserPojo;
import service.AdminService;
import service.CaretakerService;
import service.RequestService;
import service.UserService;
import validators.InputValidator;
import dao.RequestDao;
import exceptions.DateUtils;
import exceptions.GlobalExceptionHandler;
import exceptions.InvalidInputException;

public class PresentationDemo {

	private UserService userService;
	private CaretakerService caretakerService;
	private AdminService adminService;
	private RequestService requestService;

	private Scanner scanner;
	private int currentUserId;
	private int choice;

	private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public PresentationDemo(UserService userService, CaretakerService caretakerService, AdminService adminService,
			RequestService requestService) {
		this.userService = userService;
		this.caretakerService = caretakerService;
		this.adminService = adminService;
		this.requestService = requestService;
		this.scanner = new Scanner(System.in);
	}

	// method to start the presentation layer
	public void start() {
		while (true) {
			try {
				displayMainMenu();
				choice = getUserChoice();

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
					GlobalExceptionHandler.handleInvalidChoice();
					break;
				}
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e);
			} catch (NumberFormatException e) {
				GlobalExceptionHandler.handleNumberFormatException(e);
			} catch (NoSuchElementException e) {
				GlobalExceptionHandler.handleNoSuchElementException(e);
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}
		}
	}

	private int getUserChoice() {
		while (true) {
			try {
				System.out.print("Enter your choice: ");
				int choice = scanner.nextInt();
				scanner.nextLine();

				return choice;
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e);
				scanner.nextLine();
			} catch (NoSuchElementException e) {
				GlobalExceptionHandler.handleNoSuchElementException(e);
				scanner.nextLine();
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}
		}
	}

	private void displayMainMenu() {
		System.out.println("------------------------------------------------");
		System.out.println("                    Main Menu");
		System.out.println("------------------------------------------------");
		System.out.println("1. User Menu");
		System.out.println("2. Caretaker Menu");
		System.out.println("3. Admin Menu");
		System.out.println("4. Exit");
		System.out.println("------------------------------------------------");
	}

	private void userMenu() {
		while (true) {
			try {
				System.out.println("\nUser Menu:");
				System.out.println("1. Sign In/Sign Up");
				System.out.println("2. View All Nurse/Caretaker");
				System.out.println("3. Exit");
				System.out.println();

				choice = getUserChoice();

				switch (choice) {
				case 1:
					userSigninSignupMenu();
					break;

				case 2:
					listAllCaretakers();
					break;

				case 3:
					return;

				default:
					GlobalExceptionHandler.handleInvalidChoice();
					break;
				}
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}
		}
	}

	private void userSigninSignupMenu() {
		while (true) {
			try {
				System.out.println("Hire your nurse now!");
				System.out.println("1. Existing User? Sign In");
				System.out.println("2. New user? Sign Up");
				System.out.println("3. Exit");
				System.out.println();

				int choice = getUserChoice(); // Using the updated common method for user input

				switch (choice) {
				case 1:
					userLogin();
					break;

				case 2:
					createUserAccount();
					break;

				case 3:
					System.out.println("Exiting to the previous menu...");
					return;

				default:
					GlobalExceptionHandler.handleInvalidChoice();
					break;
				}
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e);
			} catch (NumberFormatException e) {
				GlobalExceptionHandler.handleNumberFormatException(e);
			} catch (NoSuchElementException e) {
				GlobalExceptionHandler.handleNoSuchElementException(e);
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}
		}
	}

	private void userLogin() {
		try {
			System.out.print("Enter User Username: ");
			String username = scanner.nextLine();

			System.out.print("Enter User Password: ");
			String password = scanner.nextLine();

			UserPojo user = userService.getUserByUsername(username);

			if (user != null && user.getPassword().equals(password)) {
				currentUserId = user.getUserId();
				System.out.println("Login successful!");

				int userId = user.getUserId();
				userOperationsAfterLogin(userId);
			} else {
				System.out.println("Invalid username or password. Please try again.");
			}
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
		}
	}

	private void userOperationsAfterLogin(int userId) {
		while (true) {
			try {
				System.out.println("\nWelcome to Nurse Sync!");
				System.out.println("1. Search Caretaker / Nurse with Preference");
				System.out.println("2. List All Caretakers");
				System.out.println("3. View My Requests Sent");
				System.out.println("4. Logout");
				System.out.print("Enter your choice: ");

				int choice = getUserChoice(); // Using a common method for getting user choice

				switch (choice) {
				case 1:
					CaretakerPreferences preferences = new CaretakerPreferences(null, null, 0, null, null, null, false,
							null);
					provideCaretakerPreferences(preferences);
					handleCaretakerSelection(userId, preferences);
					break;

				case 2:
					listAllCaretakers();
					sendRequest(userId);
					break;

				case 3:
					viewUserRequestsSent(userId);
					break;

				case 4:
					System.out.println("Logging out...");
					return;

				default:
					GlobalExceptionHandler.handleInvalidChoice(); // Handle invalid menu choices
					break;
				}
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e); // Handle input mismatch errors
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e); // Handle other general exceptions
			}
		}
	}

	private void listAllCaretakers() {
		try {
			List<CaretakerPojo> caretakers = caretakerService.getAllCaretakers();

			if (caretakers.isEmpty()) {
				System.out.println("No caretakers found.");
			} else {
				System.out.println("\n--- List of Caretakers ---");
				for (CaretakerPojo caretaker : caretakers) {
					System.out.println("ID: " + caretaker.getCaretakerId());
					System.out.println("Name: " + caretaker.getName());
					System.out.println("Gender: " + caretaker.getGender());
					System.out.println("Category: " + caretaker.getCategory());
					System.out.printf("Weekly Rate: ₹%.2f\n", caretaker.getWeeklyRate()); // Formatting currency
					System.out.println("Availability: " + caretaker.getAvailabilityFrom() + " to "
							+ caretaker.getAvailabilityTo());
					System.out.println("Location: " + caretaker.getLocation());
					System.out.println("Phone Number: " + caretaker.getPhoneNumber());
					System.out.println("Qualifications: " + caretaker.getQualifications());
					System.out.println("Live In: " + (caretaker.getLiveIn() ? "Yes" : "No")); // Formatting boolean
					System.out.println("Status: " + caretaker.getStatus());
					System.out.println("----------------------------------");
				}
			}
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handling any exceptions that might occur
		}
	}

	private void viewUserRequestsSent(int userId) {
		try {
			List<RequestPojo> requests = requestService.getRequestsByUserId(userId);

			if (requests.isEmpty()) {
				System.out.println("\nYou have not sent any requests.");
			} else {
				System.out.println("\n--- Requests Sent ---");
				for (RequestPojo request : requests) {
					System.out.println("Request ID: " + request.getRequestId());
					System.out.println("Caretaker ID: " + request.getCaretakerId());
					System.out.println("Service Location: " + request.getServiceLocation());
					System.out.println("Patient Name: " + request.getPatientName());
					System.out.println("Patient Age: " + request.getPatientAge());
					System.out.println("Patient Gender: " + request.getPatientGender());
					System.out.println("Status: " + request.getStatus());
					System.out.println("----------------------------------");
				}
			}
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
		}
	}

	private void handleCaretakerSelection(int userId, CaretakerPreferences preferences) {
		try {
			List<CaretakerPojo> matchingCaretakers = caretakerService.findCaretakersByPreferences(preferences);

			if (matchingCaretakers.isEmpty()) {
				System.out.println("\nNo caretakers found matching your preferences.");
			} else {
				System.out.println("\n--- Matching Caretakers ---");
				for (CaretakerPojo caretaker : matchingCaretakers) {
					System.out.println("ID: " + caretaker.getCaretakerId());
					System.out.println("Name: " + caretaker.getName());
					System.out.println("Gender: " + caretaker.getGender());
					System.out.println("Category: " + caretaker.getCategory());
					System.out.println("Weekly Rate: " + caretaker.getWeeklyRate());
					System.out.println("Availability From: " + caretaker.getAvailabilityFrom());
					System.out.println("Availability To: " + caretaker.getAvailabilityTo());
					System.out.println("Location: " + caretaker.getLocation());
					System.out.println("Phone Number: " + caretaker.getPhoneNumber());
					System.out.println("Qualifications: " + caretaker.getQualifications());
					System.out.println("Live In: " + caretaker.getLiveIn());
					System.out.println("Status: " + caretaker.getStatus());
					System.out.println("---------------------------------------------------");
				}
			}
			sendRequest(userId);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e); // Handle any unexpected exceptions
		}
	}

	private void sendRequest(int userId) {
		try {
			System.out.print("Enter the ID of the caretaker you want to send a request to: ");
			int caretakerId = getUserChoice();

			System.out.print("Enter Service Location: ");
			String serviceLocation = scanner.nextLine();

			System.out.print("Enter Patient Name: ");
			String patientName = scanner.nextLine();

			System.out.print("Enter Patient Age: ");
			int patientAge = getUserChoice();

			System.out.print("Enter Patient Gender: ");
			String patientGender = scanner.nextLine();

			if (requestService == null) {
				System.out.println("Error: Request service is not initialized.");
				return;
			}

			// Send the request
			boolean result = requestService.sendRequestToCaretaker(userId, caretakerId, serviceLocation, patientName,
					patientAge, patientGender);

			if (result) {
				System.out.println("Request sent successfully!");
			} else {
				System.out.println("Failed to send request. Please try again.");
			}

		} catch (InputMismatchException e) {
			GlobalExceptionHandler.handleInvalidInput();
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
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

		// Create UserPojo
		UserPojo user = new UserPojo(0, name, username, password, email, phoneNumber, address);
		boolean success = userService.createUser(user);

		if (success) {
			System.out.println("User account created successfully!");

			userLogin();
		} else {
			System.out.println("User account creation failed! Username might be taken.");
		}
	}

	private void provideCaretakerPreferences(CaretakerPreferences preferences) {
	    try {
	        // Gather user input
	        String category = InputValidator.validateCategory(scanner);
	        preferences.setCategory(category);

	        String genderPreference = InputValidator.validateGenderPreference(scanner);
	        preferences.setGenderPreference(genderPreference);

	        double maxWeeklyRate = InputValidator.validateMaxWeeklyRate(scanner);
	        preferences.setMaxWeeklyRate(maxWeeklyRate);

	        boolean validInput = false;
	        Date requiredFrom = null;
	        Date requiredTo = null;

	        while (!validInput) {
	            try {
	                String fromDateString = InputValidator.promptAndValidateDate(scanner, "Enter Required From (yyyy-MM-dd): ");
	                String toDateString = InputValidator.promptAndValidateDate(scanner, "Enter Required To (yyyy-MM-dd): ");

	                requiredFrom = DateUtils.parseSqlDate(fromDateString);
	                requiredTo = DateUtils.parseSqlDate(toDateString);

	                if (!requiredFrom.before(requiredTo)) {
	                    throw new IllegalArgumentException("The 'Required From' date must be before the 'Required To' date.");
	                }

	                preferences.setRequiredFrom(requiredFrom);
	                preferences.setRequiredTo(requiredTo);

	                validInput = true; // Exit loop on successful input
	            } catch (IllegalArgumentException e) {
	                System.out.println("\nError: " + e.getMessage());
	                System.out.println("Please try again.");
	            }
	        }

	        String serviceLocation = InputValidator.validateServiceLocation(scanner);
	        preferences.setServiceLocation(serviceLocation);

	        boolean liveIn = InputValidator.validateLiveIn(scanner);
	        preferences.setLiveIn(liveIn);

	        String forWhom = InputValidator.validateForWhom(scanner);
	        preferences.setForWhom(forWhom);

	        // Find and display matching caretakers
	        List<CaretakerPojo> matchedCaretakers = caretakerService.findCaretakersByPreferences(preferences);

	        System.out.println("\n--- Matching Caretakers ---");
	        if (matchedCaretakers.isEmpty()) {
	            System.out.println("No caretakers found matching the given preferences.");
	        } else {
	            for (CaretakerPojo caretaker : matchedCaretakers) {
	                System.out.println("---------------------------------------------------");
	                System.out.printf("Caretaker ID       : %d%n", caretaker.getCaretakerId());
	                System.out.printf("Name               : %s%n", caretaker.getName());
	                System.out.printf("Gender             : %s%n", caretaker.getGender());
	                System.out.printf("Category           : %s%n", caretaker.getCategory());
	                System.out.printf("Weekly Rate        : %.2f%n", caretaker.getWeeklyRate());
	                System.out.printf("Availability From  : %s%n", caretaker.getAvailabilityFrom());
	                System.out.printf("Availability To    : %s%n", caretaker.getAvailabilityTo());
	                System.out.printf("Location           : %s%n", caretaker.getLocation());
	                System.out.printf("Phone Number       : %s%n", caretaker.getPhoneNumber());
	                System.out.printf("Qualifications     : %s%n", caretaker.getQualifications());
	                System.out.printf("Live-In            : %b%n", caretaker.getLiveIn());
	                System.out.printf("Status             : %s%n", caretaker.getStatus());
	                System.out.println("---------------------------------------------------");
	            }
	        }
	    } catch (ParseException e) {
	        System.out.println("\nError: An error occurred while parsing dates. Please check the date format and try again.");
	        GlobalExceptionHandler.handleParseException(e);
	    } catch (InputMismatchException e) {
	        GlobalExceptionHandler.handleInputMismatchException(e);
	    } catch (NoSuchElementException e) {
	        GlobalExceptionHandler.handleNoSuchElementException(e);
	    } catch (Exception e) {
	        GlobalExceptionHandler.handleException(e);
	    }
	}

	public void caretakerMenu() {
	    while (true) {
	        try {
	            System.out.println("\n--- Start Your Freelancing Journey with Nurse Sync ---");
	            System.out.println("1. Existing User? Sign In");
	            System.out.println("2. New User? Sign Up");
	            System.out.println("3. Exit");
	            System.out.println("-----------------------------------------------");
	            System.out.print("Enter your choice: ");

	            int choice = getUserChoice(); 

	            switch (choice) {
	                case 1:
	                    System.out.println("\n--- Sign In ---");
	                    caretakerLogin();
	                    break;

	                case 2:
	                    System.out.println("\n--- Sign Up ---");
	                    caretakerSignUp();
	                    break;

	                case 3:
	                    System.out.println("\nExiting the application. Thank you for using Nurse Sync!");
	                    return;

	                default:
	                    System.out.println("\nInvalid choice. Please enter a number between 1 and 3.");
	                    break;
	            }
	        } catch (InputMismatchException e) {
	            GlobalExceptionHandler.handleInputMismatchException(e);
	        } catch (NoSuchElementException e) {
	            GlobalExceptionHandler.handleNoSuchElementException(e);
	        } catch (Exception e) {
	            GlobalExceptionHandler.handleException(e);
	        }
	    }
	}

	private void caretakerLogin() {
	    try {
	        System.out.println("\n--- Caretaker Login ---");

	        System.out.print("Enter Username: ");
	        String username = scanner.nextLine();

	        System.out.print("Enter Password: ");
	        String password = scanner.nextLine();

	        CaretakerPojo caretaker = caretakerService.getCaretakerByUsername(username);

	        if (caretaker != null && caretaker.getPassword().equals(password)) {
	            currentUserId = caretaker.getCaretakerId();
	            System.out.println("\nLogin Successful! Welcome, " + caretaker.getName() + "!");
	            
	            caretakerRequestOperations(caretaker.getCaretakerId());
	        } else {
	            System.out.println("\n--- Login Failed ---");
	            System.out.println("Invalid credentials. Please try again.");
	        }

	    } catch (Exception e) {
	        GlobalExceptionHandler.handleException(e);
	    }
	}

	private void caretakerSignUp() {
	    try {
	        System.out.println("\n--- Caretaker Sign-Up ---");

	        System.out.print("Enter your Name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter your Username: ");
	        String username = scanner.nextLine();

	        System.out.print("Enter your Password: ");
	        String password = scanner.nextLine();

	        System.out.print("Enter your Gender (Male/Female/Others): ");
	        String gender = scanner.nextLine();

	        System.out.print("Enter your Category (e.g., Nurse, Caretaker): ");
	        String category = scanner.nextLine();

	        System.out.print("Enter your Weekly Rate: ");
	        double weeklyRate = getUserInputAsDouble();

	        System.out.print("Enter Availability From (yyyy-MM-dd): ");
	        Date availabilityFrom = getDateFromUser();

	        System.out.print("Enter Availability To (yyyy-MM-dd): ");
	        Date availabilityTo = getDateFromUser();

	        System.out.print("Enter your Location: ");
	        String location = scanner.nextLine();

	        System.out.print("Enter your Phone Number: ");
	        String phoneNumber = scanner.nextLine();

	        System.out.print("Enter your Qualifications: ");
	        String qualifications = scanner.nextLine();

	        System.out.print("Are you willing to Live In? (yes/no): ");
	        boolean liveIn = getUserInputAsBoolean();

	        String status = "Available"; // Default status

	        // Create CaretakerPojo object
	        CaretakerPojo caretaker = new CaretakerPojo(
	            0, name, username, password, gender, category, weeklyRate,
	            availabilityFrom, availabilityTo, location, phoneNumber,
	            qualifications, liveIn, status
	        );

	        // Attempt to create the caretaker
	        boolean success = caretakerService.createCaretaker(caretaker);

	        if (success) {
	            System.out.println("\n--- Sign-Up Successful ---");
	            System.out.println("Caretaker account created successfully!");
	            caretakerLogin();
	        } else {
	            System.out.println("\n--- Sign-Up Failed ---");
	            System.out.println("Caretaker account creation failed! The username might be taken.");
	        }

	    } catch (IllegalArgumentException e) {
	        GlobalExceptionHandler.handleDateParseException(e);
	    } catch (InputMismatchException e) {
	        GlobalExceptionHandler.handleInputMismatchException(e);
	    } catch (NoSuchElementException e) {
	        GlobalExceptionHandler.handleNoSuchElementException(e);
	    } catch (Exception e) {
	        GlobalExceptionHandler.handleException(e);
	    }
	}

	private double getUserInputAsDouble() {
		while (true) {
			try {
				return scanner.nextDouble();
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e);
				scanner.next(); // Clear invalid input
			}
		}
	}

	private Date getDateFromUser() throws ParseException {
		while (true) {
			try {
				String dateStr = scanner.nextLine();
				return Date.valueOf(dateStr);
			} catch (IllegalArgumentException e) {
				GlobalExceptionHandler.handleDateParseException(e);
			}
		}
	}

	private boolean getUserInputAsBoolean() {
		while (true) {
			String input = scanner.nextLine().trim();
			if (input.equalsIgnoreCase("yes")) {
				return true;
			} else if (input.equalsIgnoreCase("no")) {
				return false;
			} else {
				GlobalExceptionHandler.handleInvalidBooleanInput();
			}
		}
	}

	public void caretakerRequestOperations(int caretakerID) {
		boolean keepRunning = true;

		while (keepRunning) {
			try {
				System.out.println("\n--- Caretaker Request Operations ---");
				System.out.println("1. View All Your Requests");
				System.out.println("2. Approve or Reject Requests Received");
				System.out.println("3. Exit");
				System.out.println("------------------------------------");

				int choice = getUserChoice(); 

				switch (choice) {
				case 1:
					System.out.println("\n--- Viewing All Requests ---");
					viewCaretakerRequests(caretakerID);
					break;

				case 2:
					System.out.println("\n--- Approve or Reject Requests ---");
					handleApproveOrRejectRequest(caretakerID);
					break;

				case 3:
					System.out.println("Exiting Request Operations. Thank you!");
					keepRunning = false; // Exit loop
					break;

				default:
					System.out.println("Invalid choice. Please enter a number between 1 and 3.");
					break;
				}
			} catch (InputMismatchException e) {
				GlobalExceptionHandler.handleInputMismatchException(e);
				scanner.nextLine();
			} catch (NoSuchElementException e) {
				GlobalExceptionHandler.handleNoSuchElementException(e);
				scanner.nextLine();
			} catch (IllegalStateException e) {
				GlobalExceptionHandler.handleIllegalStateException(e);
			} catch (Exception e) {
				GlobalExceptionHandler.handleException(e);
			}
		}
	}

	public void viewCaretakerRequests(int caretakerId) {
		try {
			List<RequestPojo> requests = requestService.getRequestsForCaretaker(caretakerId);

			if (requests.isEmpty()) {
				System.out.println("------------------------------------------------");
				System.out.println("                No Requests Received");
				System.out.println("------------------------------------------------");
			} else {
				System.out.println("------------------------------------------------");
				System.out.println("                Requests Received");
				System.out.println("------------------------------------------------");

				for (RequestPojo request : requests) {
					System.out.printf("Request ID       : %d%n", request.getRequestId());
					System.out.printf("User ID          : %d%n", request.getUserId());
					System.out.printf("Status           : %s%n", request.getStatus());
					System.out.printf("Service Location : %s%n", request.getServiceLocation());
					System.out.printf("Patient Name     : %s%n", request.getPatientName());
					System.out.printf("Patient Age      : %d%n", request.getPatientAge());
					System.out.printf("Patient Gender   : %s%n", request.getPatientGender());
					System.out.println("------------------------------------------------");
				}
			}
		} catch (InputMismatchException e) {
			GlobalExceptionHandler.handleInputMismatchException(e);
			scanner.nextLine();
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
			scanner.nextLine();
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
		}
	}

	private void handleApproveOrRejectRequest(int caretakerId) {
		try {
			List<RequestPojo> requests = requestService.getRequestsForCaretaker(caretakerId);

			if (requests.isEmpty()) {
				System.out.println("------------------------------------------------");
				System.out.println("                No Requests Available");
				System.out.println("------------------------------------------------");
				return;
			}

			System.out.println("------------------------------------------------");
			System.out.println("                Handle Request");
			System.out.println("------------------------------------------------");

			System.out.print("Enter the Request ID to approve or reject: ");
			int requestId = scanner.nextInt();
			scanner.nextLine(); 

			System.out.print("Approve (yes/no): ");
			String approveInput = scanner.nextLine();

			boolean approve = approveInput.equalsIgnoreCase("yes");

			if (requestService.approveOrRejectRequest(requestId, approve)) {
				System.out.printf("Request %d has been %s successfully!%n", requestId,
						approve ? "approved" : "rejected");
			} else {
				System.out.println("Failed to update request status. Please check the Request ID and try again.");
			}

		} catch (InputMismatchException e) {
			GlobalExceptionHandler.handleInputMismatchException(e);
			scanner.nextLine();
		} catch (NoSuchElementException e) {
			GlobalExceptionHandler.handleNoSuchElementException(e);
		} catch (IllegalStateException e) {
			GlobalExceptionHandler.handleIllegalStateException(e);
		} catch (Exception e) {
			GlobalExceptionHandler.handleException(e);
		}
	}
	
	private void adminMenu() {
	    try {
	        System.out.println("\n==============================");
	        System.out.println("         Admin Menu");
	        System.out.println("==============================");
	        System.out.println("1. Sign In");
	        System.out.println("2. Exit");
	        System.out.println();

	        int choice = getUserChoice(); 

	        switch (choice) {
	            case 1:
	                System.out.println("\n--- Signing In ---");
	                adminSignIn(); 
	                break;

	            case 2:
	                System.out.println("\nExiting... Thank you!");
	                return; 

	            default:
	                GlobalExceptionHandler.handleInvalidChoice(); 
	                break;
	        }
	    } catch (InputMismatchException e) {
	        GlobalExceptionHandler.handleInputMismatchException(e); 
	    } catch (NoSuchElementException e) {
	        GlobalExceptionHandler.handleNoSuchElementException(e); 
	    } catch (Exception e) {
	        GlobalExceptionHandler.handleException(e); 
	    }
	}

	
	private void adminSignIn() {
		
	}
}
