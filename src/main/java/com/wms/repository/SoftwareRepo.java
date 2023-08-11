package com.wms.repository;

import com.wms.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepo extends JpaRepository<Software, Long> {
    @Query(value = "select sum(s.price) from Software s")
    Double getTotalPrice();

    @Query(value = "select count(s.id) from Software s")
    Integer getTotalCount();
}
