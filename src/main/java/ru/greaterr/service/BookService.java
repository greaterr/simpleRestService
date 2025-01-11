package ru.greaterr.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.greaterr.dto.BookDto;
import ru.greaterr.entity.Book;
import ru.greaterr.exception.BookAlreadyExistsException;
import ru.greaterr.exception.BookNotFoundException;
import ru.greaterr.repository.BookRepository;
import ru.greaterr.utils.BookMapper;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto findById(Long id) {
        logger.debug("Trying to find book with ID: {}", id);
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            logger.error("User not found with ID: {}", id);
            return new BookNotFoundException(id);
        });
        logger.info("Book found with ID: {}", id);
        return bookMapper.toDto(book);
    }

    public List<BookDto> findAll() {
        logger.debug("Trying to find all books");
        var bookList = bookRepository.findAll();
        logger.info("Books found: {}", bookList.size());
        List<BookDto> bookDtoList = new ArrayList<>();
        for (var book : bookList) {
            bookDtoList.add(bookMapper.toDto(book));
        }
        return bookDtoList;
    }

    public BookDto saveBook(@RequestBody BookDto bookDto) {
        logger.debug("Trying to add new book with title: {} and author: {}", bookDto.getTitle(), bookDto.getAuthor());
        if(bookRepository.existsBookByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor())) {
            logger.error("Book with title: {}, and author: {} - already exists", bookDto.getTitle(), bookDto.getAuthor());
            throw new BookAlreadyExistsException(bookDto.getTitle(), bookDto.getAuthor());
        }
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

}
