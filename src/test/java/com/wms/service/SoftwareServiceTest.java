package com.wms.service;

import com.wms.entity.Software;
import com.wms.mapper.SoftwareMapper;
import com.wms.repository.SoftwareRepo;
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
class SoftwareServiceTest {
    @Mock
    private SoftwareRepo softwareRepo;

    @Mock
    private SoftwareMapper entityMapper;

    private SoftwareService softwareService;

    @BeforeEach
    void setUp() {
        Software software1 = Software.builder()
                .id(1L)
                .licenseId(1L)
                .build();

        Software software2 = Software.builder()
                .id(2L)
                .licenseId(2L)
                .build();

        softwareService = new SoftwareService(softwareRepo, entityMapper);
        when(softwareRepo.findAll()).thenReturn(List.of(software1, software2));
        when(softwareRepo.findById(1L)).thenReturn(Optional.of(software1));
        when(softwareRepo.findById(2L)).thenReturn(Optional.of(software2));
        when(softwareRepo.save(any(Software.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testFindAll() {
        List<Software> softwares = softwareService.findAll();

        assertEquals(2, softwares.size());
    }

    @Test
    void testUpdate_existingId() {
        Software updatedSoftware = Software.builder()
                .id(1L)
                .name("Updated Name")
                .build();

        when(entityMapper.updateEntity(updatedSoftware)).thenReturn(updatedSoftware);

        Software result = softwareService.update(updatedSoftware, 1L);

        assertEquals(updatedSoftware, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        Software updatedSoftware = Software.builder()
                .id(3L)
                .name("Updated Software").build();

        Software result = softwareService.update(updatedSoftware, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Software software = Software.builder().id(4L).name("new software").build();
        Software savedSoftware = softwareService.save(software);
        Assertions.assertNotNull(savedSoftware);
    }
}
