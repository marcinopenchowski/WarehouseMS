package com.wms.mapper;

import com.wms.entity.Accessory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessoryMapper extends EntityMapper<Accessory> {
}
