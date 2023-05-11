package com.wms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EntityMapper<T> {
    void updateEntity(T source, @MappingTarget T target);
}
