package com.oracle.nexteducation.literalura;

import com.oracle.nexteducation.literalura.models.Book;
import com.oracle.nexteducation.literalura.services.ApiClient;
import com.oracle.nexteducation.literalura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = "com.oracle.nexteducation.literalura")
public class Application {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApiClient apiClient) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean continueRunning = true;

			while (continueRunning) {
				try {
					System.out.println("\nWelcome to the Book Finder Application");
					System.out.println("1. Search for a book");
					System.out.println("2. List all books");
					System.out.println("3. List books by language");
					System.out.println("4. List authors with their books");
					System.out.println("5. Exit");
					System.out.print("Enter your choice: ");

					int choice = scanner.nextInt();
					scanner.nextLine();

					switch (choice) {
						case 1:
							System.out.print("Enter a keyword to search for books: ");
							String keyword = scanner.nextLine();
							apiClient.searchBooks(keyword);
							break;
						case 2:
							List<Book> books = bookService.getBooks();
							if (books.isEmpty()) {
								System.out.println("No books have been added yet.");
							} else {
								books.forEach(book -> {
									System.out.println(book);
									System.out.println();
								});
							}
							break;
						case 3:
							System.out.print("Enter the language to filter by: ");
							String language = scanner.nextLine();
							List<Book> booksByLanguage = bookService.getBooksByLanguage(language);
							if (booksByLanguage.isEmpty()) {
								System.out.println("No books found for this language.");
							} else {
								booksByLanguage.forEach(book -> {
									System.out.println(book);
									System.out.println();
								});
							}
							break;
						case 4:
							List<Book> allBooks = bookService.getBooks();
							if (allBooks.isEmpty()) {
								System.out.println("No books have been added yet.");
							} else {
								allBooks.stream()
										.collect(Collectors.groupingBy(Book::getAuthor))
										.forEach((author, booksByAuthor) -> {
											System.out.println("Author: " + author);
											booksByAuthor.forEach(book -> {
												System.out.println(" - " + book.getTitle());
											});
											System.out.println();
										});
							}
							break;
						case 5:
							continueRunning = false;
							break;
						default:
							System.out.println("Invalid option, please try again.");
					}
				} catch (InputMismatchException ime) {
					System.out.println("Please enter a valid number.");
					scanner.nextLine();
				}
			}
			scanner.close();
		};
	}
}
