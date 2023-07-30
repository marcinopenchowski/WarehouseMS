package com.wms.controller;

import com.wms.entity.Software;
import com.wms.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("software")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SoftwareController {

    private final SoftwareService softwareService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<Software> findAll() {
        return softwareService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    Software findById(@PathVariable Long id) {
        return softwareService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    Software update(@RequestBody Software software, @PathVariable Long id) {
        return softwareService.update(software, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    Software save(@RequestBody Software software) {
        return softwareService.save(software);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        softwareService.deleteById(id);
    }
}
