package com.wms.controller;

import com.wms.entity.Accessory;
import com.wms.service.AccessoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("accessory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccessoryController {

    private final AccessoryService accessoryService;

    @GetMapping
    List<Accessory> findAll() {
        return accessoryService.findAll();
    }

    @GetMapping("/{id}")
    Accessory findById(@PathVariable Long id) {
        return accessoryService.findById(id);
    }

    @PutMapping("/{id}")
    Accessory update(@RequestBody Accessory accessory, @PathVariable Long id) {
        return accessoryService.update(accessory, id);
    }

    @PostMapping()
    Accessory save(@RequestBody Accessory accessory) {
        return accessoryService.save(accessory);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        accessoryService.deleteById(id);
    }
}
