package validators;

import java.util.Scanner;

import exceptions.InvalidInputException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class InputValidator {

	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

	public static String validateCategory(Scanner scanner) {
		String category = "";
		boolean validInput = false;

		while (!validInput) {
			System.out.println("Enter Category:");
			System.out.println("1. Nurse");
			System.out.println("2. Caregiver");
			System.out.println("3. Dual-Role Nurse-Caregiver");

			System.out.print("Enter your choice (1-3): ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				category = "Nurse";
				validInput = true;
				break;
			case 2:
				category = "Caregiver";
				validInput = true;
				break;
			case 3:
				category = "Dual-Role Nurse-Caregiver";
				validInput = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}

		return category;
	}

	public static String validateGenderPreference(Scanner scanner) {
		boolean validInput = false;
		String genderPreference = "";

		while (!validInput) {
			System.out.println("Enter Gender Preference:");
			System.out.println("1. Male");
			System.out.println("2. Female");
			System.out.println("3. Any");

			System.out.print("Enter your choice (1-3): ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				genderPreference = "Male";
				validInput = true;
				break;
			case 2:
				genderPreference = "Female";
				validInput = true;
				break;
			case 3:
				genderPreference = "Any";
				validInput = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}

		return genderPreference;
	}

	public static double validateMaxWeeklyRate(Scanner scanner) {
		double maxWeeklyRate = 0.0;
		boolean validInput = false;

		while (!validInput) {
			System.out.println("Enter Max Weekly Rate:");
			System.out.println("1. ₹5000");
			System.out.println("2. ₹1000");
			System.out.println("3. Above ₹10000");

			System.out.print("Enter your choice (1-3): ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				maxWeeklyRate = 5000;
				validInput = true;
				break;
			case 2:
				maxWeeklyRate = 1000;
				validInput = true;
				break;
			case 3:
				maxWeeklyRate = 100000; // This value can be set to whatever "Above ₹10000" represents.
				validInput = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}

		return maxWeeklyRate;
	}

	public static void validateDateOrder(Date requiredFrom, Date requiredTo) throws IllegalArgumentException {
		if (requiredFrom.after(requiredTo)) {
			throw new IllegalArgumentException("The 'Required From' date cannot be after the 'Required To' date.");
		}
	}

	public static String validateServiceLocation(Scanner scanner) {
		String serviceLocation;
		while (true) {
			System.out.print("Enter new Service Location: ");
			serviceLocation = scanner.nextLine().trim(); // Trim to remove leading/trailing spaces

			// Check if the input is empty
			if (serviceLocation.isEmpty()) {
				System.out.println("Service location cannot be empty. Please enter a valid location.");
			} else {
				break; // Exit the loop if input is valid
			}
		}
		return serviceLocation;
	}

	public static boolean validateLiveIn(Scanner scanner) {
		while (true) {
			System.out.print("Is Live-In Required? (true/false): ");
			String input = scanner.nextLine().trim().toLowerCase(); // Convert to lowercase for easier comparison

			// Check if the input is "true" or "false"
			if (input.equals("true")) {
				return true;
			} else if (input.equals("false")) {
				return false;
			} else {
				System.out.println("Invalid input. Please enter 'true' or 'false'.");
			}
		}
	}

	public static String validateForWhom(Scanner scanner) {
		String forWhom = "";
		boolean validInput = false;

		while (!validInput) {
			System.out.println("For Whom is the Service?");
			System.out.println("1. Mother");
			System.out.println("2. Father");
			System.out.println("3. Self");
			System.out.println("4. Other");

			System.out.print("Enter your choice (1-4): ");
			int choice = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (choice) {
			case 1:
				forWhom = "Mother";
				validInput = true;
				break;
			case 2:
				forWhom = "Father";
				validInput = true;
				break;
			case 3:
				forWhom = "Self";
				validInput = true;
				break;
			case 4:
				System.out.print("Please specify: ");
				forWhom = scanner.nextLine();
				validInput = true;
				break;
			default:
				System.out.println("Invalid choice. Please select a valid option.");
			}
		}

		return forWhom;
	}

	public static String promptAndValidateDate(Scanner scanner, String prompt) {
		System.out.print(prompt);
		return scanner.nextLine();
	}

}
