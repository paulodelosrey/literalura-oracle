package com.oracle.nexteducation.literalura;

import com.oracle.nexteducation.literalura.models.Author;
import com.oracle.nexteducation.literalura.models.Book;
import com.oracle.nexteducation.literalura.services.ApiClient;
import com.oracle.nexteducation.literalura.services.AuthorService;
import com.oracle.nexteducation.literalura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application {

	@Autowired
	private BookService bookService;
	@Autowired
	private AuthorService authorService;
	@Autowired
	private ApiClient apiClient;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean continueRunning = true;

			while (continueRunning) {
				printMenu();
				try {
					int choice = Integer.parseInt(scanner.nextLine());

					switch (choice) {
						case 1:
							searchBooks(scanner);
							break;
						case 2:
							listAllBooks();
							break;
						case 3:
							listBooksByLanguage(scanner);
							break;
						case 4:
							listAuthorsWithBooks();
							break;
						case 5:
							listAuthorsAliveInYear(scanner);
							break;
						case 6:
							continueRunning = false;
							break;
						default:
							System.out.println("Invalid option. Please try again.");
					}
				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid number.");
				}
			}
			scanner.close();
		};
	}
	private void printMenu() {
		System.out.println("\nWelcome to the Book Finder Application");
		System.out.println("1. Search for a book");
		System.out.println("2. List all books");
		System.out.println("3. List books by language");
		System.out.println("4. List authors with their books");
		System.out.println("5. List authors alive in a specific year");
		System.out.println("6. Exit");
		System.out.print("Enter your choice: ");
	}

	private void searchBooks(Scanner scanner) {
		System.out.print("Enter a keyword to search for books: ");
		String keyword = scanner.nextLine();
		apiClient.searchBooks(keyword);
	}

	private void listAllBooks() {
		List<Book> books = bookService.getAllBooks();
		if (books.isEmpty()) {
			System.out.println("No books have been added yet.");
		} else {
			books.forEach(System.out::println);
		}
	}

	private void listBooksByLanguage(Scanner scanner) {
		System.out.print("Enter the language to filter by: ");
		String language = scanner.nextLine();
		List<Book> booksByLanguage = bookService.getBooksByLanguage(language);
		if (booksByLanguage.isEmpty()) {
			System.out.println("No books found for this language.");
		} else {
			booksByLanguage.forEach(System.out::println);
			long count = bookService.countBooksByLanguage(language);
			System.out.println("Total books in " + language + ": " + count);
		}
	}

	private void listAuthorsWithBooks() {
		List<Book> allBooks = bookService.getAllBooks();
		if (allBooks.isEmpty()) {
			System.out.println("No books have been added yet.");
		} else {
			allBooks.stream()
					.collect(Collectors.groupingBy(Book::getAuthor))
					.forEach((author, books) -> {
						System.out.println("Author: " + author.getName());
						books.forEach(book -> System.out.println(" - " + book.getTitle()));
						System.out.println();
					});
		}
	}

	private void listAuthorsAliveInYear(Scanner scanner) {
		System.out.print("Enter the year to check for living authors: ");
		int year;
		while (true) {
			try {
				year = Integer.parseInt(scanner.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.out.print("Invalid input. Please enter a valid year: ");
			}
		}

		List<Author> livingAuthors = authorService.getAuthorsAliveInYear(year);
		if (livingAuthors.isEmpty()) {
			System.out.println("No authors found alive in that year.");
		} else {
			System.out.println("Authors alive in " + year + ":");
			livingAuthors.forEach(author -> System.out.println(" - " + author.getName()));
		}
	}
}