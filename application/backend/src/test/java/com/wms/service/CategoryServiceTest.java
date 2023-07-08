package com.wms.service;

import com.wms.entity.Category;
import com.wms.mapper.CategoryMapper;
import com.wms.repository.CategoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryServiceTest {
    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private CategoryMapper entityMapper;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        Category category1 = Category.builder()
                .id(1L)
                .name("Notebook")
                .build();

        Category category2 = Category.builder()
                .id(2L)
                .name("Notebook")
                .build();

        entityMapper = mock(CategoryMapper.class);
        categoryService = new CategoryService(categoryRepo, entityMapper);
        when(categoryRepo.findAll()).thenReturn(List.of(category1, category2));
        when(categoryRepo.findById(1L)).thenReturn(Optional.of(category1));
        when(categoryRepo.findById(2L)).thenReturn(Optional.of(category2));
        when(categoryRepo.save(any(Category.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testFindAll() {
        List<Category> categories = categoryService.findAll();

        assertEquals(2, categories.size());
    }

    @Test
    void testUpdate_existingId() {
        Category updatedCategory = Category.builder()
                .id(1L)
                .name("Updated Name")
                .build();

        when(entityMapper.updateEntity(updatedCategory)).thenReturn(updatedCategory);

        Category result = categoryService.update(updatedCategory, 1L);

        assertEquals(updatedCategory, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        Category updatedCategory = Category.builder()
                .id(3L)
                .name("Updated Category").build();

        Category result = categoryService.update(updatedCategory, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Category category = Category.builder().id(4L).name("new category").build();
        Category savedCategory = categoryService.save(category);
        Assertions.assertNotNull(savedCategory);
    }
}
