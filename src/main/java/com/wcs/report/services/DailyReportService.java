package com.wcs.report.services;

import com.wcs.report.mappers.CBReportUpdateDTO;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;

import java.util.List;

public interface DailyReportService {
    DailyReportResponseDTO createDailyReport(Long userId, DailyReportDTO reportDTO);
    DailyReportResponseDTO getDailyReportById(Long userId, Long dailyReportId);

    DailyReportResponseDTO updateDailyReport(Long userId, Long dailyReportId, List<CBReportUpdateDTO> cbReportDTOS);

    List<DailyReportResponseDTO> getDailyReportsByUser(Long userId);

}
