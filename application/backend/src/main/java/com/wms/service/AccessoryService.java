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
public class AccessoryService {

    private final AccessoryRepo accessoryRepo;
    private final AccessoryMapper entityMapper;

    public List<Accessory> findAll() {
        return accessoryRepo.findAll();
    }

    public Accessory findById(Long id) {
        return accessoryRepo.findById(id).orElse(null);
    }

    public Accessory update(Accessory accessory, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(accessory))
                .map(accessoryRepo::save)
                .orElse(null);
    }

    public Accessory save(Accessory accessory) {
        return accessoryRepo.save(accessory);
    }

    public void deleteById(Long id) {
        accessoryRepo.deleteById(id);
    }
}
