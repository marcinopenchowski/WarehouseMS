package com.wms.controller;

import com.wms.entity.License;
import com.wms.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("license")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LicenseController {

    private final LicenseService licenseService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<License> findAll() {
        return licenseService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    License findById(@PathVariable Long id) {
        return licenseService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    License update(@RequestBody License license, @PathVariable Long id) {
        return licenseService.update(license, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    License save(@RequestBody License license) {
        return licenseService.save(license);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        licenseService.deleteById(id);
    }
}
