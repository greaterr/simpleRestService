package ru.greaterr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.greaterr.dto.BookDto;
import ru.greaterr.service.BookService;

import java.util.List;

@RestController
public class BooksController {
    private static final Logger logger = LoggerFactory.getLogger(BooksController.class);
    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookDto>> getBooks() {
        logger.info("Received GET request for all books");
        List<BookDto> foundBooks = bookService.findAll();
        logger.info("Returning book details for found books");
        return ResponseEntity.ok(foundBooks);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {
        logger.info("Received GET request for book with ID: {}", id);
        BookDto foundBook = bookService.findById(id);
        logger.info("Returning book details for ID: {}", id);
        return ResponseEntity.ok(foundBook);
    }

    @PostMapping("/books")
    public ResponseEntity<BookDto> saveNewBook(@RequestBody BookDto bookDto) {
        logger.info("Received POST request for save new book: {}", bookDto);
        BookDto savedBook = bookService.saveBook(bookDto);
        logger.info("New book was successfully saved with ID: {}", savedBook.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @PutMapping("/books/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.updateAuthor(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>   deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}