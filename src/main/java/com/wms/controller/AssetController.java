package com.wms.controller;

import com.wms.entity.Asset;
import com.wms.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asset")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    List<Asset> findAll() {
        return assetService.findAll();
    }

    @GetMapping("/{id}")
    Asset findById(@PathVariable Long id) {
        return assetService.findById(id);
    }

    @PutMapping("/{id}")
    Asset update(@RequestBody Asset asset, @PathVariable Long id) {
        return assetService.update(asset, id);
    }

    @PostMapping()
    Asset save(@RequestBody Asset asset) {
        return assetService.save(asset);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        assetService.deleteById(id);
    }
}
