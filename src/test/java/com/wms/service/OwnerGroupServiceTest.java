package com.wms.service;

import com.wms.entity.OwnerGroup;
import com.wms.mapper.OwnerGroupMapper;
import com.wms.repository.OwnerGroupRepo;
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
class OwnerGroupServiceTest {
    @Mock
    private OwnerGroupRepo ownerGroupRepo;

    @Mock
    private OwnerGroupMapper entityMapper;

    private OwnerGroupService ownerGroupService;

    @BeforeEach
    void setUp() {
        OwnerGroup ownerGroup1 = OwnerGroup.builder()
                .id(1L)
                .name("ownerGroup")
                .build();

        OwnerGroup ownerGroup2 = OwnerGroup.builder()
                .id(2L)
                .name("secondOwnerGroup")
                .build();

        ownerGroupService = new OwnerGroupService(ownerGroupRepo, entityMapper);
        when(ownerGroupRepo.findAll()).thenReturn(List.of(ownerGroup1, ownerGroup2));
        when(ownerGroupRepo.findById(1L)).thenReturn(Optional.of(ownerGroup1));
        when(ownerGroupRepo.findById(2L)).thenReturn(Optional.of(ownerGroup2));
        when(ownerGroupRepo.save(any(OwnerGroup.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    public void testFindAll() {
        List<OwnerGroup> ownerGroups = ownerGroupService.findAll();

        assertEquals(2, ownerGroups.size());
    }

    @Test
    void testUpdate_existingId() {
        OwnerGroup updatedOwnerGroup = OwnerGroup.builder()
                .id(1L)
                .name("Updated Owner Group")
                .build();

        when(entityMapper.updateEntity(updatedOwnerGroup)).thenReturn(updatedOwnerGroup);

        OwnerGroup result = ownerGroupService.update(updatedOwnerGroup, 1L);

        assertEquals(updatedOwnerGroup, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        OwnerGroup updatedOwnerGroup = OwnerGroup.builder()
                .id(3L)
                .name("Updated Owner Group").build();

        OwnerGroup result = ownerGroupService.update(updatedOwnerGroup, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        OwnerGroup ownerGroup = OwnerGroup.builder().id(4L).name("new Owner Group").build();
        OwnerGroup savedOwnerGroup = ownerGroupService.save(ownerGroup);
        Assertions.assertNotNull(savedOwnerGroup);
    }
}
