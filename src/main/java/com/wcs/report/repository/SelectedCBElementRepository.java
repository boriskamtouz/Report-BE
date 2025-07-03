package com.wcs.report.repository;

import com.wcs.report.entities.SelectedCBElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedCBElementRepository extends JpaRepository<SelectedCBElement, Long> {
    SelectedCBElement findByUserId(Long userId);
}
