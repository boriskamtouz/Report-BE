package com.wcs.report.repository;

import com.wcs.report.entities.DailyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
}
