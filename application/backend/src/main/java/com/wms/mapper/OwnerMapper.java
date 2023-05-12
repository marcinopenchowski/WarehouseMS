package com.wms.mapper;

import com.wms.entity.Owner;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OwnerMapper extends EntityMapper<Owner> {
}
