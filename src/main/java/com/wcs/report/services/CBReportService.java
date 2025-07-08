package com.wcs.report.services;

import com.wcs.report.mappers.CBReportUpdateDTO;
import com.wcs.report.payload.CBReportDTO;

public interface CBReportService {
    CBReportDTO createCBReport(
            Long userId,
            Long cbId,
            Long reportId,
            CBReportDTO cbReportDTO
    );

    CBReportDTO updateCBReport(
            Long reportId,
            Long cbId,
            CBReportUpdateDTO cbReportUpdateDTO
    );

}
