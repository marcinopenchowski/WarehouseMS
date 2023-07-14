package com.wms.service;

import com.wms.entity.Accessory;
import com.wms.mapper.AccessoryMapper;
import com.wms.repository.AccessoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessoryService implements EntityService<Accessory> {

    private final AccessoryRepo accessoryRepo;
    private final AccessoryMapper entityMapper;

    @Override
    public List<Accessory> findAll() {
        return accessoryRepo.findAll();
    }

    @Override
    public Accessory findById(Long id) {
        return accessoryRepo.findById(id).orElse(null);
    }

    @Override
    public Accessory update(Accessory accessory, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(accessory))
                .map(accessoryRepo::save)
                .orElse(null);
    }

    @Override
    public Accessory save(Accessory accessory) {
        return accessoryRepo.save(accessory);
    }

    @Override
    public void deleteById(Long id) {
        accessoryRepo.deleteById(id);
    }
}
