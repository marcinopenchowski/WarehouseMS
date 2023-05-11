package com.wms.service;

import com.wms.entity.License;
import com.wms.mapper.EntityMapper;
import com.wms.repository.LicenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LicenseService {

    private final LicenseRepo licenseRepo;
    private final EntityMapper<License> entityMapper;

    public List<License> findAll() {
        return licenseRepo.findAll();
    }

    public License findById(Long id) {
        return licenseRepo.findById(id).orElse(null);
    }

    public License update(License license, Long id) {
        return Optional.ofNullable(findById(id))
                .map(existedLicense -> {
                    entityMapper.updateEntity(license, existedLicense);
                    return licenseRepo.save(existedLicense);
                })
                .orElse(null);
    }

    public License save(License license) {
        return licenseRepo.save(license);
    }

    public void deleteById(Long id) {
        licenseRepo.deleteById(id);
    }
}
