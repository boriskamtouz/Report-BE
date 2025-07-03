package com.wcs.report.repository;

import com.wcs.report.entities.CBElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CBElementRepository extends JpaRepository<CBElement, Long> {
    CBElement findByCodeIgnoreCase(String code);
}
