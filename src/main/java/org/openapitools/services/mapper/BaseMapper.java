package org.openapitools.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.openapitools.jackson.nullable.JsonNullable;

public interface BaseMapper<ENTITY, DTO> {
    DTO entityToDto(ENTITY entity);
    ENTITY dtoToEntity(DTO dto);


    default <T>T map(JsonNullable<T> value) {
        return value!=null && value.isPresent() ? value.get() : null;
    }
    default <T>JsonNullable<T> map(T value) {
        return value == null ? null : JsonNullable.of(value);
    }

}

