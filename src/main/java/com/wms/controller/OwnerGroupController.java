package com.wms.controller;

import com.wms.entity.OwnerGroup;
import com.wms.service.OwnerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owner_group")
@RequiredArgsConstructor
public class OwnerGroupController {

    private final OwnerGroupService ownerGroupService;

    @GetMapping
    List<OwnerGroup> findAll() {
        return ownerGroupService.findAll();
    }

    @GetMapping("/{id}")
    OwnerGroup findById(@PathVariable Long id) {
        return ownerGroupService.findById(id);
    }

    @PutMapping("/{id}")
    OwnerGroup update(@RequestBody OwnerGroup ownerGroup, @PathVariable Long id) {
        return ownerGroupService.update(ownerGroup, id);
    }

    @PostMapping()
    OwnerGroup save(@RequestBody OwnerGroup ownerGroup) {
        return ownerGroupService.save(ownerGroup);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        ownerGroupService.deleteById(id);
    }
}
