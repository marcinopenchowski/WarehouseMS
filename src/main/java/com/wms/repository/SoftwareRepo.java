package com.wms.repository;

import com.wms.entity.Software;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareRepo extends JpaRepository<Software, Long> {
}
