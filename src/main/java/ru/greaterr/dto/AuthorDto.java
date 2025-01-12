package ru.greaterr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.greaterr.entity.Book;

import java.util.List;

@Data
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private List<Book> books;
}
