package com.wms.controller;

import com.wms.entity.Category;
import com.wms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService categoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    List<Category> findAll() {
        return categoryService.findAll();
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    Category update(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.update(category, id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
