package com.wms.service;

import com.wms.entity.Asset;
import com.wms.entity.Item;
import com.wms.mapper.AssetMapper;
import com.wms.repository.AssetRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssetService implements EntityService<Item> {

    private final AssetRepo assetRepo;
    private final AssetMapper entityMapper;

    @Override
    public List<Item> findAll() {
        return assetRepo.findAll();
    }

    @Override
    public Item findById(Long id) {
        return assetRepo.findById(id).orElse(null);
    }

    @Override
    public Item update(Item asset, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity((Asset) asset))
                .map(assetRepo::save)
                .orElse(null);
    }

    @Override
    public Item save(Item asset) {
        return assetRepo.save(asset);
    }

    @Override
    public void deleteById(Long id) {
        assetRepo.deleteById(id);
    }
}
