package com.wcs.report.services;

import com.wcs.report.payload.ReportDTO;

import java.util.List;

public interface ReportService {
    ReportDTO createReport(ReportDTO reportDTO);
    List<ReportDTO> getAllReports();
}
