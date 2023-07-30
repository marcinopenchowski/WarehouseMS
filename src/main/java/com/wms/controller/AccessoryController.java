package com.wms.controller;

import com.wms.entity.Accessory;
import com.wms.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accessory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessoryController {

    private final AccessoryService accessoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<Accessory> findAll() {
        return accessoryService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    Accessory findById(@PathVariable Long id) {
        return accessoryService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    Accessory update(@RequestBody Accessory accessory, @PathVariable Long id) {
        return accessoryService.update(accessory, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    Accessory save(@RequestBody Accessory accessory) {
        return accessoryService.save(accessory);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        accessoryService.deleteById(id);
    }
}
