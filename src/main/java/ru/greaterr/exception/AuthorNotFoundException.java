package ru.greaterr.exception;

public class AuthorNotFoundException extends RuntimeException{

    public AuthorNotFoundException(Long id) {
    super ("Author not found: " + id);
    }

    public AuthorNotFoundException(String name) {
        super ("Author not found: " + name);
    }
}
