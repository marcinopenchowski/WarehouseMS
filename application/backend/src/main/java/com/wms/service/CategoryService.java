package com.wms.service;

import com.wms.entity.Category;
import com.wms.mapper.CategoryMapper;
import com.wms.repository.CategoryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryMapper entityMapper;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category findById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category update(Category category, Long id) {
        return Optional.ofNullable(findById(id))
                .map(it -> entityMapper.updateEntity(category))
                .map(categoryRepo::save)
                .orElse(null);
    }

    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
    }
}
