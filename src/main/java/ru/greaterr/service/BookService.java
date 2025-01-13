package ru.greaterr.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.greaterr.dto.BookDto;
import ru.greaterr.entity.Book;
import ru.greaterr.exception.BookAlreadyExistsException;
import ru.greaterr.exception.BookNotFoundException;
import ru.greaterr.repository.BookRepository;
import ru.greaterr.utils.BookMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookDto findById(Long id) {
        logger.debug("Trying to find book with ID: {}", id);
        Book book = bookRepository.findById(id).orElseThrow(() -> {
            logger.debug("User not found with ID: {}", id);
            return new BookNotFoundException(id);
        });
        logger.info("Book found with ID: {}", id);
        return bookMapper.toDto(book);
    }

    public List<BookDto> findAll() {
        logger.debug("Trying to find all books");
        var bookList = bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .toList();
        logger.debug("Books found: {}", bookList.size());
        return bookList;
    }

    public BookDto saveBook(BookDto bookDto) {
        logger.debug("Trying to add new book with title: {} and author: {}", bookDto.getTitle(), bookDto.getAuthor());
        if (bookRepository.existsBookByTitleAndAuthorName(bookDto.getTitle(), bookDto.getAuthor().getName())) {
            logger.error("Book with title: {}, and author: {} - already exists", bookDto.getTitle(), bookDto.getAuthor().getName());
            throw new BookAlreadyExistsException(bookDto.getTitle(), bookDto.getAuthor());
        }
        Book book = bookMapper.toEntity(bookDto);
        Book savedBook = bookRepository.save(book);
        logger.debug("Book with ID {} successfully saved", savedBook.getId());
        return bookMapper.toDto(savedBook);
    }

    public BookDto updateAuthor(BookDto bookDto) {
        logger.debug("Validating update request for book with ID: {}", bookDto.getId());
        Book existingBook = bookRepository.findById(bookDto.getId())
                .orElseThrow(() -> {
                    logger.error("Book with ID {} does not exist", bookDto.getId());
                    return new BookNotFoundException(bookDto.getId());
                });

        existingBook.setYear(bookDto.getYear());
        Book updatedBook = bookRepository.save(existingBook);
        logger.debug("Book with ID {} successfully updated", updatedBook.getId());
        return bookMapper.toDto(updatedBook);
    }

    public void deleteBook(Long id) {
        logger.debug("Validating delete request for book with ID: {}", id);
        if (!bookRepository.existsById(id)) {
            logger.error("Book with ID {} does not exist", id);
            throw new BookNotFoundException(id);
        }
        bookRepository.deleteById(id);
        logger.debug("Book with ID {} successfully deleted", id);
    }
}
