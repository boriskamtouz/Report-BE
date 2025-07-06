package com.wcs.report.repository;

import com.wcs.report.entities.SelectedCBElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedCBElementRepository extends JpaRepository<SelectedCBElement, Long> {

    @Query("SELECT s FROM SelectedCBElement s WHERE s.userId = :userId")
    SelectedCBElement findByUserId(@Param("userId") Long userId);
}
