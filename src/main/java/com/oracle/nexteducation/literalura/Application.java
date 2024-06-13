package com.oracle.nexteducation.literalura;

import com.oracle.nexteducation.literalura.services.ApiClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.util.InputMismatchException;
import java.util.Scanner;

@SpringBootApplication
@ComponentScan(basePackages = "com.oracle.nexteducation.literalura")
public class Application {

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
					System.out.println("2. Exit");
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
