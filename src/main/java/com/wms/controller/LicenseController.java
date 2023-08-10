package com.wms.controller;

import com.wms.entity.License;
import com.wms.service.LicenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("license")
@RequiredArgsConstructor
public class LicenseController {

    private final LicenseService licenseService;

    @GetMapping
    List<License> findAll() {
        return licenseService.findAll();
    }

    @GetMapping("/{id}")
    License findById(@PathVariable Long id) {
        return licenseService.findById(id);
    }

    @PutMapping("/{id}")
    License update(@RequestBody License license, @PathVariable Long id) {
        return licenseService.update(license, id);
    }

    @PostMapping()
    License save(@RequestBody License license) {
        return licenseService.save(license);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        licenseService.deleteById(id);
    }
}
