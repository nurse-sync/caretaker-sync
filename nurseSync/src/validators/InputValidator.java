package validators;
import java.util.Scanner;

import exceptions.InvalidInputException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public class InputValidator {
	
	public static double validateMaxWeeklyRate(Scanner scanner) throws InvalidInputException {
        System.out.print("Enter new Max Weekly Rate: ");
        if (!scanner.hasNextDouble()) {
            scanner.nextLine(); // Consume the invalid input
            throw new InvalidInputException("Invalid input. Please enter a valid number for the weekly rate.");
        }
        
        double maxWeeklyRate = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        
        if (maxWeeklyRate < 0) {
            throw new InvalidInputException("Max Weekly Rate cannot be negative.");
        }

        return maxWeeklyRate;
    }
	
	public static LocalDate validateLocalDate(Scanner scanner, String prompt) throws InvalidInputException {
        System.out.print(prompt);
        String input = scanner.nextLine();

        try {
            return LocalDate.parse(input);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }
}
