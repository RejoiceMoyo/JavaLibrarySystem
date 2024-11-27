import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Class representing a book in the library
class Book {
    String title;
    String author;
    int quantity;

    // Constructor to initialize a book
    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }
}

public class LibrarySystem {
    // A map to store books in the library, using the title as the key
    private static Map<String, Book> library = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Scanner to read user input
        while (true) {
            // Display the menu
            System.out.println("\nLibrary System Menu:");
            System.out.println("1. Add Books");
            System.out.println("2. Borrow Books");
            System.out.println("3. Return Books");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();  // Read the user's menu choice
            scanner.nextLine();  // Consume the newline

            // Handle the user's choice
            switch (option) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    borrowBooks(scanner);
                    break;
                case 3:
                    returnBooks(scanner);
                    break;
                case 4:
                    System.out.println("Exiting the Library System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Method to add books to the library
    private static void addBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Check if the book already exists in the library
        if (library.containsKey(title)) {
            Book existingBook = library.get(title);
            existingBook.quantity += quantity;  // Update the quantity if it exists
            System.out.println("Updated quantity of " + title + " to " + existingBook.quantity);
        } else {
            library.put(title, new Book(title, author, quantity));  // Add new book if it doesn't exist
            System.out.println("Added new book: " + title);
        }
    }

    // Method to borrow books from the library
    private static void borrowBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter quantity to borrow: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Check if the book is available in the library
        if (library.containsKey(title)) {
            Book book = library.get(title);
            if (book.quantity >= quantity) {
                book.quantity -= quantity;  // Update the quantity if enough books are available
                System.out.println("You have successfully borrowed " + quantity + " copies of " + title);
            } else {
                System.out.println("Sorry, not enough copies available.");
            }
        } else {
            System.out.println("The book is not available in the library.");
        }
    }

    // Method to return books to the library
    private static void returnBooks(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter quantity to return: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  // Consume the newline

        // Check if the book belongs to the library
        if (library.containsKey(title)) {
            Book book = library.get(title);
            book.quantity += quantity;  // Update the quantity on return
            System.out.println("You have successfully returned " + quantity + " copies of " + title);
        } else {
            System.out.println("The book does not belong to this library.");
        }
    }
}
