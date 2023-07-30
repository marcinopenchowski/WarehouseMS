package com.wms.controller;

import com.wms.entity.Asset;
import com.wms.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asset")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssetController {

    private final AssetService assetService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<Asset> findAll() {
        return assetService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    Asset findById(@PathVariable Long id) {
        return assetService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    Asset update(@RequestBody Asset asset, @PathVariable Long id) {
        return assetService.update(asset, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    Asset save(@RequestBody Asset asset) {
        return assetService.save(asset);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        assetService.deleteById(id);
    }
}
