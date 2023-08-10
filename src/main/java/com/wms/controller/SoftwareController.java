package com.wms.controller;

import com.wms.entity.Software;
import com.wms.service.SoftwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("software")
@RequiredArgsConstructor
public class SoftwareController {

    private final SoftwareService softwareService;

    @GetMapping
    List<Software> findAll() {
        return softwareService.findAll();
    }

    @GetMapping("/{id}")
    Software findById(@PathVariable Long id) {
        return softwareService.findById(id);
    }

    @PutMapping("/{id}")
    Software update(@RequestBody Software software, @PathVariable Long id) {
        return softwareService.update(software, id);
    }

    @PostMapping()
    Software save(@RequestBody Software software) {
        return softwareService.save(software);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        softwareService.deleteById(id);
    }
}
