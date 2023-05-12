package com.wms.service;

import com.wms.entity.License;
import com.wms.mapper.LicenseMapper;
import com.wms.repository.LicenseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LicenseService implements EntityService<License> {

    private final LicenseRepo licenseRepo;
    private final LicenseMapper entityMapper;

    @Override
    public List<License> findAll() {
        return licenseRepo.findAll();
    }

    @Override
    public License findById(Long id) {
        return licenseRepo.findById(id).orElse(null);
    }

    @Override
    public License update(License license, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(license))
                .map(licenseRepo::save)
                .orElse(null);
    }

    @Override
    public License save(License license) {
        return licenseRepo.save(license);
    }

    @Override
    public void deleteById(Long id) {
        licenseRepo.deleteById(id);
    }
}
