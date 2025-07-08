package com.wcs.report.services.impl;

import com.wcs.report.entities.*;
import com.wcs.report.mappers.CBReportMapper;
import com.wcs.report.mappers.CBReportUpdateDTO;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.repository.CBReportRepository;
import com.wcs.report.repository.DailyReportRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.CBReportService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    private final DailyReportRepository dailyReportRepository;

    @Autowired
    public CBReportServiceImpl(
            UserRepository userRepository,
            CBReportRepository cbReportRepository,
            CBReportMapper cbReportMapper,
            DailyReportRepository dailyReportRepository
    ) {
        this.userRepository = userRepository;
        this.cbReportRepository = cbReportRepository;
        this.cbReportMapper = cbReportMapper;
        this.dailyReportRepository = dailyReportRepository;
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

        DailyReport report = dailyReportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        CBReport cbReport = new CBReport();
        cbReport.setCbElement(cbElementToReport);
        cbReport.setVal(cbReportDTO.getVal());
        cbReport.setDailyReport(report);

        CBReport cbReportSaved = cbReportRepository.save(cbReport);

        return cbReportMapper.toDTO(cbReportSaved);
    }

    @Transactional
    @Override
    public CBReportDTO updateCBReport(Long reportId, Long cbId, CBReportUpdateDTO cbReportUpdateDTO) {
        DailyReport dailyReport = dailyReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("DailyReport not found"));

        CBReport cbReportToUpdate = dailyReport.getCbReports()
                .stream().filter(cbReport -> cbReport.getCbElement().getId().equals(cbId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("CBReport not found"));

        cbReportToUpdate.setVal(cbReportToUpdate.getVal());
        CBReport cbReport = cbReportRepository.save(cbReportToUpdate);
        dailyReportRepository.save(dailyReport);
        return cbReportMapper.toDTO(cbReport);
    }

}
