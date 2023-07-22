package com.wms.service;

import com.wms.entity.License;
import com.wms.mapper.LicenseMapper;
import com.wms.repository.LicenseRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
class LicenseServiceTest {
    @Mock
    private LicenseRepo licenseRepo;

    @Mock
    private LicenseMapper entityMapper;

    @InjectMocks
    private LicenseService licenseService;

    @BeforeEach
    void setUp() {
        License license1 = License.builder()
                .id(1L)
                .expirationDate(Date.valueOf("2023-10-14"))
                .email("jan.kowalski@gmail.com")
                .build();

        License license2 = License.builder()
                .id(2L)
                .expirationDate(Date.valueOf("2019-01-01"))
                .email("andrzej.nowak@gmail.com")
                .build();

        entityMapper = mock(LicenseMapper.class);
        licenseService = new LicenseService(licenseRepo, entityMapper);
        when(licenseRepo.findAll()).thenReturn(List.of(license1, license2));
        when(licenseRepo.findById(1L)).thenReturn(Optional.of(license1));
        when(licenseRepo.findById(2L)).thenReturn(Optional.of(license2));
        when(licenseRepo.save(any(License.class))).thenAnswer(invocation -> invocation.getArgument(0));

    }

    @Test
    void testFindAll() {
        List<License> licenses = licenseService.findAll();

        assertEquals(2, licenses.size());
    }

    @Test
    void testUpdate_existingId() {
        License updatedLicense = License.builder()
                .id(1L)
                .email("jan.nowak@gmail.com")
                .build();

        when(entityMapper.updateEntity(updatedLicense)).thenReturn(updatedLicense);

        License result = licenseService.update(updatedLicense, 1L);

        assertEquals(updatedLicense, result);
    }

    @Test
    void testUpdate_nonExistingId() {
        License updatedLicense = License.builder()
                .id(3L)
                .email("jan.nowak@gmail.com").build();

        License result = licenseService.update(updatedLicense, 3L);

        assertNull(result);
    }

    @Test
    void testSave() {
        License license = License.builder().id(4L).email("jan.nowak@gmail.com").build();
        License savedLicense = licenseService.save(license);
        Assertions.assertNotNull(savedLicense);
    }
}
