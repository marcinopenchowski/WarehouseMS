package com.wms.service;

import com.wms.repository.AccessoryRepo;
import com.wms.repository.AssetRepo;
import com.wms.repository.SoftwareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final AssetRepo assetRepo;
    private final AccessoryRepo accessoryRepo;
    private final SoftwareRepo softwareRepo;

    public Double getTotalPrice() {
        return assetRepo.getTotalPrice()
                + accessoryRepo.getTotalPrice()
                + softwareRepo.getTotalPrice();
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
