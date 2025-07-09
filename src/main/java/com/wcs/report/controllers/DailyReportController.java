package com.wcs.report.controllers;

import com.wcs.report.mappers.CBReportUpdateDTO;
import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;
import com.wcs.report.services.DailyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
public class DailyReportController {

    private final DailyReportService reportService;

    @Autowired
    public DailyReportController(DailyReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("users/{userId}/dailyReports")
    public ResponseEntity<DailyReportResponseDTO> createReport(
            @PathVariable(name = "userId") Long userId,
            @RequestBody DailyReportDTO reportDTO
    ) {
        return new ResponseEntity<>(reportService.createDailyReport(userId, reportDTO), HttpStatus.CREATED);
    }

    @PutMapping("users/{userId}/dailyReports/{reportId}")
    public ResponseEntity<DailyReportResponseDTO> updateReport(
            @PathVariable Long userId,
            @PathVariable Long reportId,
            @RequestBody List<CBReportUpdateDTO> cbReportUpdateDTOs
    ) {
        return new ResponseEntity<>(reportService.updateDailyReport(userId, reportId, cbReportUpdateDTOs), HttpStatus.OK);
    }

    @GetMapping("users/{userId}/dailyReports")
    public ResponseEntity<List<DailyReportResponseDTO>> getDailyReportsByUser(
            @PathVariable(name = "userId") Long userId
    ) {
        return new ResponseEntity<>(reportService.getDailyReportsByUser(userId), HttpStatus.OK);
    }


    @GetMapping("users/{userId}/{dailyReportId}")
    public ResponseEntity<DailyReportResponseDTO> getDailyReportById(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "dailyReportId") Long dailyReportId
    ) {
        return new ResponseEntity<>(reportService.getDailyReportById(userId, dailyReportId), HttpStatus.OK);
    }

}


