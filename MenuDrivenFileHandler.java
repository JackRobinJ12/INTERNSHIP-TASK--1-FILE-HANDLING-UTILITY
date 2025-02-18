import java.io.*;
import java.util.Scanner;

public class MenuDrivenFileHandler {

    // Read a file
    public static void readFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\nContents of the file '" + fileName + "':");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Write to a file (overwrites existing content)
    public static void writeFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(content);
            System.out.println("\nContent written to file '" + fileName + "' successfully.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Append to a file
    public static void appendToFile(String fileName, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(content + "\n");
            System.out.println("\nContent appended to file '" + fileName + "' successfully.");
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }

    // Modify a specific line in a file
    public static void modifyFile(String fileName, int lineNumber, String newContent) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder fileContent = new StringBuilder();
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    fileContent.append(newContent).append("\n");
                } else {
                    fileContent.append(line).append("\n");
                }
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(fileContent.toString());
            writer.close();

            System.out.println("\nLine " + lineNumber + " modified in file '" + fileName + "' successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the File Handler Program!");

        // Ask the user for the filename
        System.out.print("\nEnter the filename: ");
        String fileName = scanner.nextLine();

        while (true) {
            // Display the menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Read the file");
            System.out.println("2. Write to the file");
            System.out.println("3. Append to the file");
            System.out.println("4. Modify a specific line in the file");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    // Read the file
                    readFile(fileName);
                    break;

                case 2:
                    // Write to the file
                    System.out.print("\nEnter the content to write: ");
                    String writeContent = scanner.nextLine();
                    writeFile(fileName, writeContent);
                    break;

                case 3:
                    // Append to the file
                    System.out.print("\nEnter the content to append: ");
                    String appendContent = scanner.nextLine();
                    appendToFile(fileName, appendContent);
                    break;

                case 4:
                    // Modify a specific line in the file
                    System.out.print("\nEnter the line number to modify: ");
                    int lineNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter the new content for line " + lineNumber + ": ");
                    String newContent = scanner.nextLine();
                    modifyFile(fileName, lineNumber, newContent);
                    break;

                case 5:
                    // Exit the program
                    System.out.println("\nExiting the program. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}