package exceptions;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

public class GlobalExceptionHandler {

    // Handle general exceptions
    public static void handleException(Exception e) {
        System.out.println("Oops! Something went wrong. Please try again.");
        e.printStackTrace();
    }

    public static void handleInputMismatchException(InputMismatchException e) {
        System.out.println("It looks like you entered the wrong type of data. Please enter a number or correct value.");
    }

    public static void handleNumberFormatException(NumberFormatException e) {
        System.out.println("The number format you entered is invalid. Please enter a valid number.");
    }

    public static void handleNoSuchElementException(NoSuchElementException e) {
        System.out.println("It seems there was an issue with the input. Please make sure to provide the required information.");
    }

    public static void handleInvalidChoice() {
        System.out.println("Invalid choice. Please select a valid option from the menu.");
    }
    
    public static void handleInvalidInput() {
        System.out.println("Error: Invalid input. Please enter a valid value.");
    }

    public static void handleDateParseException(IllegalArgumentException e) {
        System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
    }
    
    public static void handleInvalidBooleanInput() {
        System.out.println("Invalid input. Please enter 'yes' or 'no'.");
    }

    public static void handleIllegalStateException(IllegalStateException e) {
        System.out.println("Illegal state encountered. Please try again.");
    }
    
    public static void handleParseException(ParseException e) {
        System.out.println("\nError: An error occurred while parsing dates. Please check the date format and try again.");
        e.printStackTrace();
    }
}
