package com.wcs.report.services;

import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;

import java.util.List;

public interface DailyReportService {
    DailyReportResponseDTO createDailyReport(Long userId, DailyReportDTO reportDTO);
    List<DailyReportDTO> getAllReports();
}
