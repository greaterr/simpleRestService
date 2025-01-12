package ru.greaterr.exception;

import ru.greaterr.entity.Author;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String title, Author author) {
        super("Book with title: " + title + " and author: " + author.getName() + " - already exists");
    }
}
