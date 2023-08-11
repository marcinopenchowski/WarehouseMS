package com.wms.repository;

import com.wms.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepo extends JpaRepository<Accessory, Long> {
    @Query(value = "select sum(a.price) from Asset a")
    Double getTotalPrice();

    @Query(value = "select count(a.id) from Accessory a")
    Integer getTotalCount();
}
