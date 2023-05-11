package com.wms.service;

import com.wms.entity.OwnerGroup;
import com.wms.mapper.EntityMapper;
import com.wms.repository.OwnerGroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerGroupService {

    private final OwnerGroupRepo ownerGroupRepo;
    private final EntityMapper<OwnerGroup> entityMapper;

    public List<OwnerGroup> findAll() {
        return ownerGroupRepo.findAll();
    }

    public OwnerGroup findById(Long id) {
        return ownerGroupRepo.findById(id).orElse(null);
    }

    public OwnerGroup update(OwnerGroup ownerGroup, Long id) {
        return Optional.ofNullable(findById(id))
                .map(existedOwnerGroup -> {
                    entityMapper.updateEntity(ownerGroup, existedOwnerGroup);
                    return ownerGroupRepo.save(existedOwnerGroup);
                })
                .orElse(null);
    }

    public OwnerGroup save(OwnerGroup ownerGroup) {
        return ownerGroupRepo.save(ownerGroup);
    }

    public void deleteById(Long id) {
        ownerGroupRepo.deleteById(id);
    }
}
