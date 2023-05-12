package com.wms.service;

import com.wms.entity.Asset;
import com.wms.mapper.AssetMapper;
import com.wms.repository.AssetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssetService {

    private final AssetRepo assetRepo;
    private final AssetMapper entityMapper;

    public List<Asset> findAll() {
        return assetRepo.findAll();
    }

    public Asset findById(Long id) {
        return assetRepo.findById(id).orElse(null);
    }

    public Asset update(Asset asset, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(asset))
                .map(assetRepo::save)
                .orElse(null);
    }

    public Asset save(Asset asset) {
        return assetRepo.save(asset);
    }

    public void deleteById(Long id) {
        assetRepo.deleteById(id);
    }
}
