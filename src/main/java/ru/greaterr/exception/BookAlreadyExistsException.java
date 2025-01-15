package ru.greaterr.exception;

import ru.greaterr.dto.AuthorDto;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String title, AuthorDto author) {
        super("Book with title: " + title + " and author: " + author.getName() + " - already exists");
    }
}
