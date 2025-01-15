package ru.greaterr.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    @JsonIgnore
    private List<BookDto> books;
}
