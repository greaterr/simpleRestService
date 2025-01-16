package ru.greaterr.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.greaterr.dto.AuthorDto;
import ru.greaterr.entity.Author;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {
    Author toEntity(AuthorDto authorDto);

    AuthorDto toDto(Author authorEntity);
}