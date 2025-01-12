package ru.greaterr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.greaterr.dto.AuthorDto;
import ru.greaterr.service.AuthorService;
import ru.greaterr.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorsController {
    private static final Logger logger = LoggerFactory.getLogger(AuthorsController.class);
    private final AuthorService authorService;

    public AuthorsController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDto>> getBooks() {
        logger.info("Received GET request for all authors");
        List<AuthorDto> foundAuthors = authorService.findAll();
        logger.info("Returning author details for found authors");
        return ResponseEntity.ok(foundAuthors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> findById(@PathVariable Long id) {
        logger.info("Received GET request for author with ID: {}", id);
        AuthorDto foundAuthor = authorService.findById(id);
        logger.info("Returning author details for ID: {}", id);
        return ResponseEntity.ok(foundAuthor);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDto> saveNewAuthor(@RequestBody AuthorDto authorDto) {
        logger.info("Received POST request for save new author: {}", authorDto);
        AuthorDto savedAuthor = authorService.saveAuthor(authorDto);
        logger.info("New author was successfully saved with ID: {}", savedAuthor.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @PutMapping("/update")
    public ResponseEntity<AuthorDto> updateAuthor(@RequestBody AuthorDto authorDto) {
        AuthorDto updatedAuthor = authorService.updateAuthor(authorDto);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthors(id);
        return ResponseEntity.noContent().build();
    }
}