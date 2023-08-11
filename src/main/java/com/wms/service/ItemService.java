package com.wms.service;

import com.wms.repository.AccessoryRepo;
import com.wms.repository.AssetRepo;
import com.wms.repository.SoftwareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final AssetRepo assetRepo;
    private final AccessoryRepo accessoryRepo;
    private final SoftwareRepo softwareRepo;

    public Double getTotalPrice() {
        return Optional.ofNullable(assetRepo.getTotalPrice()).orElse(0.0)
                + Optional.ofNullable(accessoryRepo.getTotalPrice()).orElse(0.0)
                + Optional.ofNullable(softwareRepo.getTotalPrice()).orElse(0.0);
    }

    public Integer getTotalCount() {
        return assetRepo.getTotalCount()
                + accessoryRepo.getTotalCount()
                + softwareRepo.getTotalCount();
    }

    public Double getTotalAverage() {
        return getTotalPrice() / getTotalCount();
    }
}
