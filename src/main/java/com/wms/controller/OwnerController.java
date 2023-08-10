package com.wms.controller;

import com.wms.entity.Owner;
import com.wms.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    List<Owner> findAll() {
        return ownerService.findAll();
    }

    @GetMapping("/{id}")
    Owner findById(@PathVariable Long id) {
        return ownerService.findById(id);
    }

    @PutMapping("/{id}")
    Owner update(@RequestBody Owner owner, @PathVariable Long id) {
        return ownerService.update(owner, id);
    }

    @PostMapping()
    Owner save(@RequestBody Owner owner) {
        return ownerService.save(owner);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        ownerService.deleteById(id);
    }
}
