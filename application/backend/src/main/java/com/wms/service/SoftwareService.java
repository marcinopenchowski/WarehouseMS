package com.wms.service;

import com.wms.entity.Software;
import com.wms.mapper.EntityMapper;
import com.wms.repository.SoftwareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoftwareService {

    private final SoftwareRepo softwareRepo;
    private final EntityMapper<Software> entityMapper;

    public List<Software> findAll() {
        return softwareRepo.findAll();
    }

    public Software findById(Long id) {
        return softwareRepo.findById(id).orElse(null);
    }

    public Software update(Software software, Long id) {
        return Optional.ofNullable(findById(id))
                .map(existedSoftware -> {
                    entityMapper.updateEntity(software, existedSoftware);
                    return softwareRepo.save(existedSoftware);
                })
                .orElse(null);
    }

    public Software save(Software software) {
        return softwareRepo.save(software);
    }

    public void deleteById(Long id) {
        softwareRepo.deleteById(id);
    }
}
