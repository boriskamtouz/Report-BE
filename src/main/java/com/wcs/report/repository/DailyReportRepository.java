package com.wcs.report.repository;

import com.wcs.report.entities.DailyReport;
import com.wcs.report.entities.User;
import com.wcs.report.payload.DailyReportResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DailyReportRepository extends JpaRepository<DailyReport, Long> {
    Optional<DailyReport> findByIdAndUser(Long userId, User user);
}
