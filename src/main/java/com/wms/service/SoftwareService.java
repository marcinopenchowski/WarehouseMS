package com.wms.service;

import com.wms.entity.Software;
import com.wms.mapper.SoftwareMapper;
import com.wms.repository.SoftwareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoftwareService implements EntityService<Software> {

    private final SoftwareRepo softwareRepo;
    private final SoftwareMapper entityMapper;

    @Override
    public List<Software> findAll() {
        return softwareRepo.findAll();
    }

    @Override
    public Software findById(Long id) {
        return softwareRepo.findById(id).orElse(null);
    }

    @Override
    public Software update(Software software, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(software))
                .map(softwareRepo::save)
                .orElse(null);
    }

    @Override
    public Software save(Software software) {
        return softwareRepo.save(software);
    }

    @Override
    public void deleteById(Long id) {
        softwareRepo.deleteById(id);
    }
}
