package ru.greaterr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.greaterr.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    boolean existsAuthorByName(String name);

    boolean existsAuthorByNameIgnoreCase(String  name);

    @Query("select a FROM Author a where a.name ILIKE :name")
    Author findByNameIgnoreCase(String name);
}
