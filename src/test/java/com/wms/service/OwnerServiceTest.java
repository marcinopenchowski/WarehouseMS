package com.wms.service;

import com.wms.entity.Owner;
import com.wms.mapper.OwnerMapper;
import com.wms.repository.OwnerRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class OwnerServiceTest {
    @MockBean
    private OwnerRepo ownerRepo;

    @Mock
    private OwnerMapper entityMapper;

    private OwnerService ownerService;

    @BeforeEach
    void serUp() {
        Owner owner1 = Owner.builder()
                .id(1L)
                .firstName("Jan")
                .lastName("Kowalski")
                .build();

        Owner owner2 = Owner.builder()
                .id(2L)
                .firstName("Andrzej")
                .lastName("Nowak")
                .build();

        ownerService = new OwnerService(ownerRepo, entityMapper);
        when(ownerRepo.findAll()).thenReturn(List.of(owner1, owner2));
        when(ownerRepo.findById(1L)).thenReturn(Optional.of(owner1));
        when(ownerRepo.findById(2L)).thenReturn(Optional.of(owner2));
        when(ownerRepo.save(any(Owner.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testFindAll() {
        List<Owner> owners = ownerService.findAll();

        assertEquals(2, owners.size());
    }

    @Test
    void testUpdate_existingId() {
        Owner updatedOwner = Owner.builder()
                .id(1L)
                .firstName("Updated")
                .lastName("Owner")
                .build();

        when(entityMapper.updateEntity(updatedOwner)).thenReturn(updatedOwner);

        Owner result = ownerService.update(updatedOwner, 1L);

        assertEquals(updatedOwner, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        Owner updatedOwner = Owner.builder()
                .id(3L)
                .firstName("Updated")
                .lastName("Owner")
                .build();

        Owner result = ownerService.update(updatedOwner, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Owner owner = Owner.builder()
                .id(4L)
                .firstName("Saved")
                .lastName("Owner")
                .build();
        Owner savedOwner = ownerService.save(owner);
        Assertions.assertNotNull(savedOwner);
    }
}
