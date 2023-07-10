package com.wms.service;

import com.wms.entity.Item;
import com.wms.entity.Software;
import com.wms.mapper.SoftwareMapper;
import com.wms.repository.SoftwareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoftwareService implements EntityService<Item> {

    private final SoftwareRepo softwareRepo;
    private final SoftwareMapper entityMapper;

    @Override
    public List<Item> findAll() {
        return softwareRepo.findAll();
    }

    @Override
    public Item findById(Long id) {
        return softwareRepo.findById(id).orElse(null);
    }

    @Override
    public Item update(Item software, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity((Software) software))
                .map(softwareRepo::save)
                .orElse(null);
    }

    @Override
    public Item save(Item software) {
        return softwareRepo.save(software);
    }

    @Override
    public void deleteById(Long id) {
        softwareRepo.deleteById(id);
    }
}
