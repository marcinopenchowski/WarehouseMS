package com.wms.service;

import com.wms.entity.Owner;
import com.wms.mapper.OwnerMapper;
import com.wms.repository.OwnerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService implements EntityService<Owner> {

    private final OwnerRepo ownerRepo;
    private final OwnerMapper entityMapper;

    @Override
    public List<Owner> findAll() {
        return ownerRepo.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepo.findById(id).orElse(null);
    }

    @Override
    public Owner update(Owner owner, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(owner))
                .map(ownerRepo::save)
                .orElse(null);
    }

    @Override
    public Owner save(Owner owner) {
        return ownerRepo.save(owner);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepo.deleteById(id);
    }
}
