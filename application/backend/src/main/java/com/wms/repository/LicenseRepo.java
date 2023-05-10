package com.wms.repository;

import com.wms.entity.License;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseRepo extends JpaRepository<License, Long> {
}
