package ru.greaterr.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long id) {
    super ("Book not found " + id);
    }
}
