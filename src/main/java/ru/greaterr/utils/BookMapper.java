package ru.greaterr.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.greaterr.dto.BookDto;
import ru.greaterr.entity.Book;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {
    Book toEntity(BookDto bookDto);
    BookDto toDto(Book bookEntity);
}