package com.oracle.nexteducation.literalura.repositories;

import com.oracle.nexteducation.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByLanguage(String language);
    long countByLanguage(String language);
}