package com.wms.controller;

import com.wms.entity.Category;
import com.wms.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    List<Category> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    Category findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PutMapping("/{id}")
    Category update(@RequestBody Category category, @PathVariable Long id) {
        return categoryService.update(category, id);
    }

    @PostMapping()
    Category save(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        categoryService.deleteById(id);
    }
}
