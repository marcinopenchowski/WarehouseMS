package com.wms.service;

import com.wms.entity.Accessory;
import com.wms.entity.Category;
import com.wms.entity.CategoryName;
import com.wms.entity.Owner;
import com.wms.repository.AccessoryRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AccessoryServiceTest {
    @Autowired
    private AccessoryService accessoryService;

    @MockBean
    private AccessoryRepo accessoryRepo;

    private final Accessory accessory1 = Accessory.builder()
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
            .purchaseCost(300.00)
            .purchaseDate(LocalDate.of(2019, 5, 3))
            .build();
    private final Accessory accessory2 = Accessory.builder()
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
            .purchaseCost(450.00)
            .purchaseDate(LocalDate.of(2020, 8, 10))
            .build();

    @Test
    public void testFindAll() {
        List<Accessory> accessories = new ArrayList<>();
        accessories.add(accessory1);
        accessories.add(accessory2);

        assertEquals(accessories, accessoryRepo.findAll(), "Should return correctly all accessories");
    }

    @Test
    public void testFindById() {
        when(accessoryRepo.findById(2L)).thenReturn(Optional.of(accessory2));
        Accessory result = accessoryService.findById(2L);

        assertEquals(accessory2.getName(), result.getName(), "Should find correctly accessory by id");
    }

    @Test
    public void testUpdate() {
        accessoryService.update(accessory2, 1L);

        assertEquals(accessory2.getPrice(), accessory1.getPrice(), "Should update accessory correctly");
    }

    @Test
    public void testSave() {
        Accessory accessoryToSave = Accessory.builder().id(3L).name("test").build();

        when(accessoryRepo.save(accessoryToSave)).thenReturn(accessoryToSave);
        Accessory expectedAccessory = accessoryService.save(accessoryToSave);

        assertEquals(expectedAccessory, accessoryToSave, "Should save accessory correctly");
    }

    @Test
    public void testDeleteById() {
        accessoryService.deleteById(1L);
        verify(accessoryRepo, times(1)).deleteById(1L);

        assertEquals(0, accessoryRepo.count(), "Should remove accessory correctly");
    }
}
