package com.wms.service;

import com.wms.entity.OwnerGroup;
import com.wms.mapper.OwnerGroupMapper;
import com.wms.repository.OwnerGroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerGroupService implements EntityService<OwnerGroup> {

    private final OwnerGroupRepo ownerGroupRepo;
    private final OwnerGroupMapper entityMapper;

    @Override
    public List<OwnerGroup> findAll() {
        return ownerGroupRepo.findAll();
    }

    @Override
    public OwnerGroup findById(Long id) {
        return ownerGroupRepo.findById(id).orElse(null);
    }

    @Override
    public OwnerGroup update(OwnerGroup ownerGroup, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(ownerGroup))
                .map(ownerGroupRepo::save)
                .orElse(null);
    }

    @Override
    public OwnerGroup save(OwnerGroup ownerGroup) {
        return ownerGroupRepo.save(ownerGroup);
    }

    @Override
    public void deleteById(Long id) {
        ownerGroupRepo.deleteById(id);
    }
}
