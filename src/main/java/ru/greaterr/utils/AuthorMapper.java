package ru.greaterr.utils;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.greaterr.dto.AuthorDto;
import ru.greaterr.entity.Author;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    Author toEntity(AuthorDto bookDto);
    AuthorDto toDto(Author author);
}