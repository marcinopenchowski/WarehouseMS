package com.wms.controller;

import com.wms.entity.OwnerGroup;
import com.wms.service.OwnerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owner_group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerGroupController {

    private final OwnerGroupService ownerGroupService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<OwnerGroup> findAll() {
        return ownerGroupService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    OwnerGroup findById(@PathVariable Long id) {
        return ownerGroupService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    OwnerGroup update(@RequestBody OwnerGroup ownerGroup, @PathVariable Long id) {
        return ownerGroupService.update(ownerGroup, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    OwnerGroup save(@RequestBody OwnerGroup ownerGroup) {
        return ownerGroupService.save(ownerGroup);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        ownerGroupService.deleteById(id);
    }
}
