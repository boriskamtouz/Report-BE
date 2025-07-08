package com.wcs.report.controllers;

import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.services.CBReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CBReportController {
    private final CBReportService cbReportService;

    @Autowired
    public CBReportController(CBReportService cbReportService) {
        this.cbReportService = cbReportService;
    }

    @PostMapping("{userId}/{cbId}/{reportId}/cbReport")
    public ResponseEntity<CBReportDTO> createCBReport(
            @PathVariable(name = "userId") Long userId,
            @PathVariable(name = "cbId") Long cbId,
            @PathVariable(name = "reportId") Long reportId,
            @RequestBody CBReportDTO cbReportDTO
    ) {
        return new ResponseEntity<>(cbReportService.createCBReport(userId, cbId, reportId, cbReportDTO), HttpStatus.CREATED);
    }
}
