package com.wcs.report.repository;

import com.wcs.report.entities.CBReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CBReportRepository extends JpaRepository<CBReport, Long> {
}
