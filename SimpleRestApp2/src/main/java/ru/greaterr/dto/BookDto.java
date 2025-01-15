package ru.greaterr.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private AuthorDto author;
    private Integer year;
}


