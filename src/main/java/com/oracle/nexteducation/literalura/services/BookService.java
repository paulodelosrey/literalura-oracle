package com.oracle.nexteducation.literalura.services;

import com.oracle.nexteducation.literalura.models.Author;
import com.oracle.nexteducation.literalura.models.Book;
import com.oracle.nexteducation.literalura.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public long countBooksByLanguage(String language) {
        return bookRepository.countByLanguage(language);
    }

    public Map<Author, List<Book>> getAuthorsWithBooks() {
        return bookRepository.findAll().stream()
                .collect(Collectors.groupingBy(Book::getAuthor));
    }
}