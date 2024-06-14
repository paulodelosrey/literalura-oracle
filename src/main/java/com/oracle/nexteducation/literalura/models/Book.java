package com.oracle.nexteducation.literalura.models;

public class Book {
    private String title;
    private String author;
    private String language;
    private int downloadCount;
    private Integer birthYear;  // Año de nacimiento puede ser null
    private Integer deathYear;  // Año de fallecimiento puede ser null

    public Book(String title, String author, String language, int downloadCount, Integer birthYear, Integer deathYear) {
        this.title = title;
        this.author = author;
        this.language = language;
        this.downloadCount = downloadCount;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    @Override
    public String toString() {
        return "Book Details:\n" +
                "Title: " + title + "\n" +
                "Author: " + author + "\n" +
                "Language: " + language + "\n" +
                "Download Count: " + downloadCount + "\n";
    }
}

