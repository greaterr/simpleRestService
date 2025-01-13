package ru.greaterr.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.greaterr.dto.AuthorDto;
import ru.greaterr.entity.Author;
import ru.greaterr.repository.AuthorRepository;
import ru.greaterr.utils.AuthorMapper;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorService.class);
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorDto findById(Long id) {
        logger.debug("Trying to find author with ID: {}", id);
        Author author = authorRepository.findById(id).orElseThrow(() -> {
            logger.debug("Author not found with ID: {}", id);
            return new IllegalArgumentException("Author not found");
        });
        logger.info("Author found with ID: {}", id);
        return authorMapper.toDto(author);
    }

    public List<AuthorDto> findAll() {
        logger.debug("Trying to find all authors");
        var authorList = authorRepository.findAll()
                .stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
        logger.debug("Author found: {}", authorList.size());
        return authorList;
    }

    public AuthorDto saveAuthor(AuthorDto authorDto) {
        logger.debug("Trying to add new author with name: {}", authorDto.getName());
        if (authorRepository.existsAuthorByName(authorDto.getName())) {
            logger.error("Author with name: {} - already exists", authorDto.getName());
            throw new IllegalArgumentException("Author with ID: " + authorDto.getId() + " does not exist");
        }
        Author author = authorMapper.toEntity(authorDto);
        Author savedAuthor = authorRepository.save(author);
        logger.debug("Author with ID {} successfully saved", savedAuthor.getId());
        return authorMapper.toDto(savedAuthor);
    }

    public AuthorDto updateAuthor(AuthorDto authorDto) {
        logger.debug("Validating update request for author with ID: {}", authorDto.getId());
        Author existingAuthor = authorRepository.findById(authorDto.getId())
                .orElseThrow(() -> {
                    logger.error("Author with ID {} does not exist", authorDto.getId());
                    return new IllegalArgumentException("Author with ID {} does not exist");
                });

        existingAuthor.setName(authorDto.getName());
        Author updatedAuthor = authorRepository.save(existingAuthor);
        logger.debug("Author with ID {} successfully updated", updatedAuthor.getId());
        return authorMapper.toDto(updatedAuthor);
    }

    public void deleteAuthors(Long id) {
        logger.debug("Validating delete request for author with ID: {}", id);
        if (!authorRepository.existsById(id)) {
            logger.error("Author with ID {} does not exist", id);
            throw new IllegalArgumentException("Author with ID {} does not exist");
        }
        authorRepository.deleteById(id);
        logger.debug("Author with ID {} successfully deleted", id);
    }

    public AuthorDto findAuthorIgnoreRegistry(String name) {
        logger.debug("Validating find request for author with name: {}", name);
        if (!authorRepository.existsAuthorByNameIgnoreCase(name)) {
            logger.error("Author with name {} does not exist", name);
            throw new IllegalArgumentException("Author with name {} does not exist");
        }
        var foundAuthor = authorRepository.findByNameIgnoreCase(name);
        logger.debug("Author with name {} successfully found", foundAuthor.getName());
        return  authorMapper.toDto(foundAuthor);
    }
}
