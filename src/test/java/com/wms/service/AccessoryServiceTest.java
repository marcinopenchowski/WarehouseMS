package com.wms.service;

import com.wms.entity.*;
import com.wms.entity.enums.CategoryName;
import com.wms.mapper.AccessoryMapper;
import com.wms.repository.AccessoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccessoryServiceTest {
    @Mock
    private AccessoryRepo accessoryRepo;

    @Mock
    private AccessoryMapper entityMapper;

    private AccessoryService accessoryService;

    @BeforeEach
    void setUp() {
        Accessory accessory1 = Accessory.builder()
                .id(1L)
                .name("STEELSERIES Arctis 9")
                .owner(
                        Owner.builder()
                                .id(1L)
                                .firstName("Jan")
                                .lastName("Kowalski")
                                .build()
                )
                .category(
                        Category.builder()
                                .id(1L)
                                .name(CategoryName.HEADPHONES.getName())
                                .build()
                )
                .description("Słuchawki bezprzewodowe SteelSeries")
                .purchaseDate(LocalDate.of(2019, 5, 3))
                .build();
        Accessory accessory2 = Accessory.builder()
                .id(2L)
                .name("Logitech MX Master 3")
                .owner(
                        Owner.builder()
                                .id(2L)
                                .firstName("Andrzej")
                                .lastName("Nowak")
                                .build()
                )
                .category(
                        Category.builder()
                                .id(2L)
                                .name(CategoryName.MOUSE.getName())
                                .build()
                )
                .description("Myszka bezprzewodowa SteelSeries")
                .purchaseDate(LocalDate.of(2020, 8, 10))
                .build();

        accessoryService = new AccessoryService(accessoryRepo, entityMapper);
        when(accessoryRepo.findAll()).thenReturn(List.of(accessory1, accessory2));
        when(accessoryRepo.findById(1L)).thenReturn(Optional.of(accessory1));
        when(accessoryRepo.findById(2L)).thenReturn(Optional.of(accessory2));
        when(accessoryRepo.save(any(Accessory.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void testFindAll() {
        List<Accessory> accessories = accessoryService.findAll();

        assertEquals(2, accessories.size());
    }

    @Test
    void testUpdate_existingId() {
        Accessory updatedAccessory = Accessory.builder()
                .id(1L)
                .name("Updated Name")
                .build();

        when(entityMapper.updateEntity(updatedAccessory)).thenReturn(updatedAccessory);

        Accessory result = accessoryService.update(updatedAccessory, 1L);

        assertEquals(updatedAccessory, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        Accessory updatedAccessory = Accessory.builder()
                .id(3L)
                .name("Updated Accessory").build();

        Accessory result = accessoryService.update(updatedAccessory, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        Accessory accessory = Accessory.builder().id(4L).name("new accessory").build();
        Accessory savedAccessory = accessoryService.save(accessory);
        Assertions.assertNotNull(savedAccessory);
    }
}
