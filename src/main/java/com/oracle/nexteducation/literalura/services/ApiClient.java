package com.oracle.nexteducation.literalura.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiClient {
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
                JsonArray authorsArray = firstBook.getAsJsonArray("authors");
                String author = authorsArray.size() > 0 ? authorsArray.get(0).getAsJsonObject().get("name").getAsString() : "No author listed";
                JsonArray languages = firstBook.getAsJsonArray("languages");
                String language = languages.size() > 0 ? languages.get(0).getAsString() : "No language listed";
                int downloadCount = firstBook.get("download_count").getAsInt();

                System.out.println("Book Details:");
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Language: " + language);
                System.out.println("Download Count: " + downloadCount);
            } else {
                System.out.println("No books found in the results.");
            }
        } else {
            System.out.println("The response is not a JSON Object.");
        }
    }
}

