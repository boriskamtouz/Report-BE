package com.wcs.report.controllers;

import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;
import com.wcs.report.services.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class DailyReportController {

    private final DailyReportService reportService;

    @Autowired
    public DailyReportController(DailyReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("{userId}/reports")
    public ResponseEntity<DailyReportResponseDTO> createReport(
            @PathVariable(name = "userId") Long userId,
            @RequestBody DailyReportDTO reportDTO
    ) {
        return new ResponseEntity<>(reportService.createDailyReport(userId, reportDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DailyReportDTO>> getAllReports() {
        return new ResponseEntity<>(reportService.getAllReports(), HttpStatus.OK);
    }

}


