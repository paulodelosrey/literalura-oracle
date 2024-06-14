package com.oracle.nexteducation.literalura.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.oracle.nexteducation.literalura.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Service
public class ApiClient {
    private final BookService bookService;

    @Autowired
    public ApiClient(BookService bookService) {
        this.bookService = bookService;
    }

    public void searchBooks(String keyword) {
        try {
            String encodedKeyword = URLEncoder.encode(keyword, StandardCharsets.UTF_8.toString());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://gutendex.com/books?search=" + encodedKeyword))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.ALWAYS)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            processResponse(response.body());
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during API request or response handling: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private void processResponse(String responseBody) {
        JsonElement jsonElement = JsonParser.parseString(responseBody);
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");
            if (results != null && !results.isEmpty()) {
                JsonObject firstBook = results.get(0).getAsJsonObject();
                String title = firstBook.get("title").getAsString();
                String language = firstBook.getAsJsonArray("languages").get(0).getAsString();
                int downloadCount = firstBook.get("download_count").getAsInt();

                String authorName = "Unknown Author";
                Integer birthYear = null;
                Integer deathYear = null;

                JsonArray authorsArray = firstBook.getAsJsonArray("authors");
                if (authorsArray != null && authorsArray.size() > 0) {
                    JsonObject author = authorsArray.get(0).getAsJsonObject();
                    authorName = author.get("name").getAsString();
                    birthYear = author.has("birth_year") && !author.get("birth_year").isJsonNull() ? author.get("birth_year").getAsInt() : null;
                    deathYear = author.has("death_year") && !author.get("death_year").isJsonNull() ? author.get("death_year").getAsInt() : null;
                }

                Book book = new Book(title, authorName, language, downloadCount, birthYear, deathYear);
                bookService.addBook(book);
                System.out.println(book);
                System.out.println("Book added!");
            } else {
                System.out.println("No books found.");
            }
        } else {
            System.out.println("Response is not a JSON object.");
        }
    }




}


