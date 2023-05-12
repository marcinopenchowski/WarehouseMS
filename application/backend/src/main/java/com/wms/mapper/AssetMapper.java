package com.wms.mapper;

import com.wms.entity.Asset;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AssetMapper extends EntityMapper<Asset> {
}
