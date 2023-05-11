package com.wms.service;

import com.wms.entity.Owner;
import com.wms.mapper.EntityMapper;
import com.wms.repository.OwnerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerService {

    private final OwnerRepo ownerRepo;
    private final EntityMapper<Owner> entityMapper;

    public List<Owner> findAll() {
        return ownerRepo.findAll();
    }

    public Owner findById(Long id) {
        return ownerRepo.findById(id).orElse(null);
    }

    public Owner update(Owner owner, Long id) {
        return Optional.ofNullable(findById(id))
                .map(existedOwner -> {
                    entityMapper.updateEntity(owner, existedOwner);
                    return ownerRepo.save(existedOwner);
                })
                .orElse(null);
    }

    public Owner save(Owner owner) {
        return ownerRepo.save(owner);
    }

    public void deleteById(Long id) {
        ownerRepo.deleteById(id);
    }
}
