package com.wms.mapper;

import com.wms.entity.Software;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SoftwareMapper extends EntityMapper<Software> {
}
