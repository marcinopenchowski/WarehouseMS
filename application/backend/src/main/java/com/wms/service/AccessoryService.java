package com.wms.service;

import com.wms.entity.Accessory;
import com.wms.entity.Item;
import com.wms.mapper.AccessoryMapper;
import com.wms.repository.AccessoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessoryService implements EntityService<Item> {

    private final AccessoryRepo accessoryRepo;
    private final AccessoryMapper entityMapper;

    @Override
    public List<Item> findAll() {
        return accessoryRepo.findAll();
    }

    @Override
    public Item findById(Long id) {
        return accessoryRepo.findById(id).orElse(null);
    }

    @Override
    public Item update(Item accessory, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity((Accessory) accessory))
                .map(accessoryRepo::save)
                .orElse(null);
    }

    @Override
    public Item save(Item accessory) {
        return accessoryRepo.save(accessory);
    }

    @Override
    public void deleteById(Long id) {
        accessoryRepo.deleteById(id);
    }
}
