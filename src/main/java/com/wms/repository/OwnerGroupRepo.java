package com.wms.repository;

import com.wms.entity.OwnerGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerGroupRepo extends JpaRepository<OwnerGroup, Long> {
}
