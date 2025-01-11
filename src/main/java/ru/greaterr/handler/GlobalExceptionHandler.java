package ru.greaterr.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.greaterr.exception.BookAlreadyExistsException;
import ru.greaterr.exception.BookNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBookNotFound(BookNotFoundException e){
        Map<String, String> responceMap = new HashMap<>();
        responceMap.put("Error: ", e.getMessage());
        return new ResponseEntity<>(responceMap, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleBookAlreadyExists(BookAlreadyExistsException e){
        Map<String, String> responceMap = new HashMap<>();
        responceMap.put("Error: ", e.getMessage());
        return new ResponseEntity<>(responceMap, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleAllExceptions(Exception e){
        Map<String, String> responceMap = new HashMap<>();
        responceMap.put("Error: ", e.getMessage());
        return new ResponseEntity<>(responceMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
