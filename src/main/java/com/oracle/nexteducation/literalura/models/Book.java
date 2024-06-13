package com.oracle.nexteducation.literalura.models;

public class Book {
        private String title;
        private String author;
        private String language;
        private int downloadCount;

        public Book(String title, String author, String language, int downloadCount) {
            this.title = title;
            this.author = author;
            this.language = language;
            this.downloadCount = downloadCount;
        }

        @Override
        public String toString() {
            return "Book Details:\n" +
                    "Title: " + title + "\n" +
                    "Author: " + author + "\n" +
                    "Language: " + language + "\n" +
                    "Download Count: " + downloadCount;
        }
}

