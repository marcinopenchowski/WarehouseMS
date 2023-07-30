package com.wms.controller;

import com.wms.entity.Owner;
import com.wms.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owner")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OwnerController {

    private final OwnerService ownerService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<Owner> findAll() {
        return ownerService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    Owner findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    Owner update(@RequestBody Owner owner, @PathVariable Long id) {
        return ownerService.update(owner, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    Owner save(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        ownerService.deleteById(id);
    }
}
