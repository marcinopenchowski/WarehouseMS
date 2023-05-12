package com.wms.mapper;

import com.wms.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<Category> {
}
