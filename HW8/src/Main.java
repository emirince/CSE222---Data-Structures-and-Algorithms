import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Formatter for parsing and formatting date-time strings
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        SocialNetworkGraph socialNetwork = new SocialNetworkGraph(); // Create a new social network graph
        Scanner scanner = new Scanner(System.in); // Scanner for reading user input

        while (true) { // Loop to display the menu repeatedly
            System.out.println("\n===== Social Network Analysis Menu =====");
            System.out.println("1. Add person");
            System.out.println("2. Remove person");
            System.out.println("3. Add friendship");
            System.out.println("4. Remove friendship");
            System.out.println("5. Find shortest path");
            System.out.println("6. Suggest friends");
            System.out.println("7. Count clusters");
            System.out.println("8. Exit");
            System.out.print("Please select an option: ");

            try {
                int choice = scanner.nextInt(); // Read user's choice
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        // Add a person to the network
                        try {
                            System.out.print("\nEnter name: ");
                            String name = scanner.nextLine(); // Read name
                            System.out.print("Enter age: ");
                            int age = scanner.nextInt(); // Read age
                            scanner.nextLine();  // Consume newline
                            System.out.print("Enter hobbies (comma-separated): ");
                            List<String> hobbies = Arrays.asList(scanner.nextLine().split(",")); // Read hobbies
                            socialNetwork.addPerson(name, age, hobbies); // Add person to the network
                        } catch (Exception e) {
                            System.out.println("Error adding person");
                            scanner.nextLine(); // Clear the buffer in case of invalid input
                        }
                        break;
                    case 2:
                        // Remove a person from the network
                        try {
                            System.out.print("\nEnter name: ");
                            String name = scanner.nextLine(); // Read name
                            System.out.print("Enter timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), formatter); // Read timestamp
                            socialNetwork.removePerson(name, timestamp); // Remove person from the network
                        } catch (Exception e) {
                            System.out.println("Error removing person");
                        }
                        break;
                    case 3:
                        // Add a friendship
                        try {
                            System.out.print("\nEnter first person's name: ");
                            String name1 = scanner.nextLine(); // Read first person's name
                            System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp1 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read first person's timestamp
                            System.out.print("Enter second person's name: ");
                            String name2 = scanner.nextLine(); // Read second person's name
                            System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp2 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read second person's timestamp
                            socialNetwork.addFriendship(name1, timestamp1, name2, timestamp2); // Add friendship
                        } catch (Exception e) {
                            System.out.println("Error adding friendship");
                        }
                        break;
                    case 4:
                        // Remove a friendship
                        try {
                            System.out.print("\nEnter first person's name: ");
                            String name1 = scanner.nextLine(); // Read first person's name
                            System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp1 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read first person's timestamp
                            System.out.print("Enter second person's name: ");
                            String name2 = scanner.nextLine(); // Read second person's name
                            System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp2 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read second person's timestamp
                            socialNetwork.removeFriendship(name1, timestamp1, name2, timestamp2); // Remove friendship
                        } catch (Exception e) {
                            System.out.println("Error removing friendship");
                        }
                        break;
                    case 5:
                        // Find the shortest path between two people
                        try {
                            System.out.print("\nEnter first person's name: ");
                            String name1 = scanner.nextLine(); // Read first person's name
                            System.out.print("Enter first person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp1 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read first person's timestamp
                            System.out.print("Enter second person's name: ");
                            String name2 = scanner.nextLine(); // Read second person's name
                            System.out.print("Enter second person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp2 = LocalDateTime.parse(scanner.nextLine(), formatter); // Read second person's timestamp
                            socialNetwork.findShortestPath(name1, timestamp1, name2, timestamp2); // Find shortest path
                        } catch (Exception e) {
                            System.out.println("Error finding shortest path");
                        }
                        break;
                    case 6:
                        // Suggest friends for a person
                        try {
                            System.out.print("\nEnter person's name: ");
                            String name = scanner.nextLine(); // Read person's name
                            System.out.print("Enter person's timestamp (yyyy-MM-dd HH:mm:ss): ");
                            LocalDateTime timestamp = LocalDateTime.parse(scanner.nextLine(), formatter); // Read person's timestamp
                            System.out.print("Enter maximum number of friends to suggest: ");
                            int maxSuggestions = scanner.nextInt(); // Read maximum number of suggestions
                            socialNetwork.suggestFriends(name, timestamp, maxSuggestions); // Suggest friends
                        } catch (Exception e) {
                            System.out.println("Error suggesting friends");
                        }
                        break;
                    case 7:
                        // Count clusters in the network
                        try {
                            socialNetwork.countClusters(); // Count clusters
                        } catch (Exception e) {
                            System.out.println("Error counting clusters");
                        }
                        break;
                    case 8:
                        // Exit the program
                        System.out.println("\nExiting...");
                        scanner.close(); // Close the scanner
                        return; // Exit the program
                    default:
                        System.out.println("\nInvalid option. Please try again."); // Handle invalid menu option
                }
            } catch (Exception e) {
                System.out.println("An error occurred");
                scanner.nextLine();  // Consume the newline to prevent an infinite loop in case of input mismatch
            }
        }
    }
}