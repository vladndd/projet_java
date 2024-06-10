// package core;

// import java.util.Scanner;

// public class Main {
// public static void main(String[] args) {
// Scanner scanner = new Scanner(System.in);
// System.out.println("Welcome to the Space Adventure Game!");
// System.out.println("1. Start new game\n2. Load game\n3. Exit");
// System.out.print("Enter your choice: ");
// int choice = scanner.nextInt();

// Game game = null;
// switch (choice) {
// case 1:
// game = new Game();
// game.play();
// break;
// case 2:
// System.out.print("Enter the filename to load: ");
// String filename = scanner.next();
// try {
// game = Game.loadGame(filename);
// game.play();
// } catch (Exception e) {
// System.out.println("Failed to load game: " + e.getMessage());
// }
// break;
// case 3:
// System.out.println("Exiting game. Goodbye!");
// System.exit(0);
// break;
// default:
// System.out.println("Invalid choice.");
// break;
// }
// scanner.close();
// }
// }
