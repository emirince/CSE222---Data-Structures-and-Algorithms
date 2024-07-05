import java.util.Scanner;

// Main class to manage the file system through user interactions
public class Main {

    static Directory currentDirectory; // Current directory in the file system
    static FileSystem fs; // File system instance

    // Main method to start the program
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            fs = new FileSystem();
            currentDirectory = fs.getRoot();
            mainMenu(scanner);
        }
    }

    // Display the main menu and handle user input
    private static void mainMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== File System Management Menu =====");
            System.out.println("1. Change directory");
            System.out.println("2. List directory contents");
            System.out.println("3. Create file/directory");
            System.out.println("4. Delete file/directory");
            System.out.println("5. Move file/directory");
            System.out.println("6. Search file/directory");
            System.out.println("7. Print directory tree");
            System.out.println("8. Sort contents by date created");
            System.out.println("9. Exit");
            System.out.print("Please select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (option) {
                case 1:
                    changeDirectory(scanner);
                    break;
                case 2:
                    listDirectoryContents();
                    break;
                case 3:
                    createFileSystemElement(scanner);
                    break;
                case 4:
                    deleteFileSystemElement(scanner);
                    break;
                case 5:
                    moveFileSystemElement(scanner);
                    break;
                case 6:
                    searchFileSystemElement(scanner);
                    break;
                case 7:
                    printDirectoryTree();
                    break;
                case 8:
                    sortContentsByDate();
                    break;
                case 9:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    // Change the current directory based on user input
    private static void changeDirectory(Scanner scanner) {
        System.out.println("Current directory: " + fs.getPath(currentDirectory));
        System.out.print("Enter new directory path: ");
        String path = scanner.nextLine();
        currentDirectory = fs.changeDirectory(path, currentDirectory);
    }

    // List the contents of the current directory
    private static void listDirectoryContents() {
        System.out.println("Listing contents of: " + fs.getPath(currentDirectory));
        fs.listDirectoryContents(currentDirectory);
    }

    // Create a new file or directory based on user input
    private static void createFileSystemElement(Scanner scanner) {
        System.out.println("Current directory: " + fs.getPath(currentDirectory));
        System.out.print("Create file or directory (f/d): ");
        String type = scanner.nextLine();
        System.out.print("Enter name for new " + (type.equals("f") ? "file: " : "directory: "));
        String name = scanner.nextLine();
        if (type.equals("f")) {
            fs.createFile(name, currentDirectory);
        } else if (type.equals("d")) {
            fs.createDirectory(name, currentDirectory);
        } else {
            System.out.println("Invalid option.");
        }
    }

    // Delete a file or directory based on user input
    private static void deleteFileSystemElement(Scanner scanner) {
        System.out.println("Current directory: " + fs.getPath(currentDirectory));
        System.out.print("Enter name of file/directory to delete: ");
        String nameToDelete = scanner.nextLine();
        fs.deleteFileOrDirectory(nameToDelete, currentDirectory);
    }

    // Move a file or directory based on user input
    private static void moveFileSystemElement(Scanner scanner) {
        System.out.println("Current directory: " + fs.getPath(currentDirectory));
        System.out.print("Enter the name of file/directory to move: ");
        String nameToMove = scanner.nextLine();
        System.out.print("Enter new directory path: ");
        String newPath = scanner.nextLine();
        fs.moveFileOrDirectory(nameToMove, newPath, currentDirectory);
    }

    // Search for a file or directory based on user input
    private static void searchFileSystemElement(Scanner scanner) {
        System.out.print("Search query: ");
        String query = scanner.nextLine();
        fs.searchFileOrDirectory(query);
    }

    // Print the directory tree starting from the current directory
    private static void printDirectoryTree() {
        System.out.println("Path to current directory from root:");
        fs.printDirectoryTree(currentDirectory);
    }

    // Sort the contents of the current directory by date created
    private static void sortContentsByDate() {
        System.out.println("Sorted contents of " + fs.getPath(currentDirectory) + " by date created:");
        fs.sortContentsByDateCreated(currentDirectory);
    }
}
