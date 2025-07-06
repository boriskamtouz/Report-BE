package com.wcs.report.repository;

import com.wcs.report.entities.CBElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CBElementRepository extends JpaRepository<CBElement, Long> {
    CBElement findByCodeIgnoreCase(String code);

    boolean existsByCode(String code);

    List<CBElement> findByCode(String code);
}
