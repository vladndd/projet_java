package utility;

import java.util.Scanner;

/**
 * Utility class provides a static Scanner instance for user input.
 * Ensure to call Utility.scanner.close() in your application's shutdown logic
 * to avoid resource leaks.
 */
public class Utility {
    public static final Scanner scanner = new Scanner(System.in);

    /**
     * Closes the Scanner instance.
     * This method should be called in the application's shutdown logic to release
     * resources.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
