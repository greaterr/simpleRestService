package ru.greaterr.utils;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.greaterr.dto.BookDto;
import ru.greaterr.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toEntity(BookDto bookDto);
    @Mapping(target = "author.books", ignore = true)
    BookDto toDto(Book bookEntity);
}