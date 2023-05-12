package com.wms.mapper;

import com.wms.entity.License;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LicenseMapper extends EntityMapper<License> {
}
