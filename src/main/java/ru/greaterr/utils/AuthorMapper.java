package ru.greaterr.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.greaterr.dto.AuthorDto;
import ru.greaterr.entity.Author;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    // @Mapping(source = "books", target = "books")
    Author toEntity(AuthorDto authorDto);
    @Mapping(target = "books.author", ignore = true)
    AuthorDto toDto(Author authorEntity);
}