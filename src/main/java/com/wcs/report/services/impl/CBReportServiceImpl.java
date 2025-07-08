package com.wcs.report.services.impl;

import com.wcs.report.entities.*;
import com.wcs.report.mappers.CBReportMapper;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.repository.CBReportRepository;
import com.wcs.report.repository.DailyReportRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.CBReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CBReportServiceImpl implements CBReportService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final CBReportRepository cbReportRepository;
    private final CBReportMapper cbReportMapper;
    private final DailyReportRepository reportRepository;

    @Autowired
    public CBReportServiceImpl(UserRepository userRepository, CBReportRepository cbReportRepository, CBReportMapper cbReportMapper, DailyReportRepository reportRepository) {
        this.userRepository = userRepository;
        this.cbReportRepository = cbReportRepository;
        this.cbReportMapper = cbReportMapper;
        this.reportRepository = reportRepository;
    }


    @Override
    public CBReportDTO createCBReport(
            Long userId,
            Long cbId,
            Long reportId,
            CBReportDTO cbReportDTO
    ) {
        User foundedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SelectedCBElement selectedCBElements = foundedUser.getSelectedCBElement();
        Set<CBElement> cbElements = selectedCBElements.getCbElements();

        CBElement cbElementToReport = cbElements.stream()
                .filter(cbElement -> cbElement.getId().equals(cbId)).findFirst()
                .orElseThrow(() -> new RuntimeException("CBElement not found"));

        DailyReport report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        CBReport cbReport = new CBReport();
        cbReport.setCbElement(cbElementToReport);
        cbReport.setVal(cbReportDTO.getVal());
        cbReport.setReport(report);

        CBReport cbReportSaved = cbReportRepository.save(cbReport);

        return cbReportMapper.toDTO(cbReportSaved);
    }

    @Override
    public CBReportDTO updateCBReport(Long cbReportId, String newValue) {
        CBReport foundedCBReport = cbReportRepository.findById(cbReportId)
                .orElseThrow(() -> new RuntimeException("CBReport not found"));

        foundedCBReport.setVal(newValue);

        CBReport cbReport = cbReportRepository.save(foundedCBReport);

        return cbReportMapper.toDTO(cbReport);
    }
}
