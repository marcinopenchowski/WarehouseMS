package com.wms.service;

import com.wms.entity.*;
import com.wms.mapper.AssetMapper;
import com.wms.repository.AssetRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AssetServiceTest {
    @Mock
    private AssetRepo assetRepo;

    @Mock
    private AssetMapper entityMapper;

    @InjectMocks
    private AssetService assetService;

    @BeforeEach
    void setUp() {
        Asset asset1 = Asset.builder().id(1L).name("name1").build();
        Asset asset2 = Asset.builder().id(2L).name("name2").build();

        entityMapper = mock(AssetMapper.class);
        assetService = new AssetService(assetRepo, entityMapper);
        when(assetRepo.findAll()).thenReturn(List.of(asset1, asset2));
        when(assetRepo.findById(1L)).thenReturn(Optional.of(asset1));
        when(assetRepo.findById(2L)).thenReturn(Optional.of(asset2));
        when(assetRepo.save(any(Asset.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testFindAll() {
        List<Asset> result = assetService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    void testFindById_existingId() {
        Asset result = assetService.findById(1L);

        assertEquals("name1", result.getName());
    }

    @Test
    void testFindById_nonExistingId() {
        Asset result = assetService.findById(3L);

        assertNull(result);
        verify(assetRepo).findById(3L);
    }

    @Test
    void testUpdate_existingId() {
        Asset updatedAsset = Asset.builder()
                .id(1L)
                .name("Updated Name")
                .build();

        when(entityMapper.updateEntity(updatedAsset)).thenReturn(updatedAsset);

        Asset result = assetService.update(updatedAsset, 1L);

        assertEquals(updatedAsset, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        Asset updatedAsset = Asset.builder().id(3L).name("updated name").build();

        Asset result = assetService.update(updatedAsset, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Asset asset = Asset.builder().id(4L).name("new asset").build();
        Asset savedAsset = assetService.save(asset);
        Assertions.assertNotNull(savedAsset);
    }
}
