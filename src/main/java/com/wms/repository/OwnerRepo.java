package com.wms.repository;

import com.wms.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {

    @Query(value = "select count(a.id) from Owner a")
    Integer getTotalCount();
}
