package ru.greaterr.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.greaterr.entity.Author;


@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private Author author;
    private Integer year;

}


