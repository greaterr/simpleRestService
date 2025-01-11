package ru.greaterr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.greaterr.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsBookByTitleAndAuthor(String title, String author);
}
