package com.wms.repository;

import com.wms.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepo extends JpaRepository<Accessory, Long> {
}
