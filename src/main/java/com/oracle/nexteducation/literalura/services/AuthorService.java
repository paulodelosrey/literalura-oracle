package com.oracle.nexteducation.literalura.services;

import com.oracle.nexteducation.literalura.models.Author;
import com.oracle.nexteducation.literalura.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> getAuthorsAliveInYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }
}