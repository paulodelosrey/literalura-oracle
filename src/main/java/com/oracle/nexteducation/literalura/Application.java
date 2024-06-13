package com.oracle.nexteducation.literalura;

import com.oracle.nexteducation.literalura.services.ApiClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				Scanner scanner = new Scanner(System.in);
				boolean continueRunning = true;

				while (continueRunning) {
					try {
						System.out.println("\nWelcome to the Book Finder Application");
						System.out.println("1. Search for a book");
						System.out.println("2. Exit");
						System.out.print("Enter your choice: ");

						int choice = scanner.nextInt();
						scanner.nextLine();

						switch (choice) {
							case 1:
								searchBook(scanner);
								break;
							case 2:
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
			}

			private void searchBook(Scanner scanner) {
				System.out.print("Enter a keyword to search for books: ");
				String keyword = scanner.nextLine();
				ApiClient apiClient = new ApiClient();
				apiClient.searchBooks(keyword);
			}
		};
	}
}
