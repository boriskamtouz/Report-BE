package com.wcs.report.services.impl;

import com.wcs.report.entities.*;
import com.wcs.report.mappers.CBReportMapper;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;
import com.wcs.report.repository.DailyReportRepository;
import com.wcs.report.repository.SelectedCBElementRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.DailyReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DailyReportServiceImpl implements DailyReportService {
    private final DailyReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CBReportMapper cbReportMapper;
    private final SelectedCBElementRepository selectedCBElementRepository;


    @Autowired
    public DailyReportServiceImpl(
            DailyReportRepository reportRepository,
            UserRepository userRepository,
            ModelMapper modelMapper, CBReportMapper cbReportMapper, SelectedCBElementRepository selectedCBElementRepository
    ) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.cbReportMapper = cbReportMapper;
        this.selectedCBElementRepository = selectedCBElementRepository;
    }

    @Override
    public DailyReportResponseDTO createDailyReport(Long userId, DailyReportDTO reportDTO) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        DailyReport report = new DailyReport();
        report.setCreatedAt(reportDTO.getCreatedAt());
        report.setUpdatedAt(reportDTO.getUpdatedAt());
        report.setUser(user);

        Set<CBReport> cbReports = new HashSet<>();

       SelectedCBElement selectedCBElement = selectedCBElementRepository.findByUserId(userId);

       Set<CBElement> userCBElements = selectedCBElement.getCbElements();

        System.out.println("User selected: " + userCBElements);


        if (userCBElements == null || userCBElements.isEmpty()) {
            throw new RuntimeException("User has not selected CB elements");
        }

        for (CBElement cbElement : userCBElements) {
            CBReport cbReport = new CBReport();
            cbReport.setVal("0"); // TODO: Voir cette valeur ne doit pas changer
            cbReport.setCbElement(cbElement);
            cbReports.add(cbReport);
        }

        report.setCbReports(cbReports);

        DailyReport dailyReport = reportRepository.save(report);

        DailyReportResponseDTO dailyReportResponseDTO = new DailyReportResponseDTO();
        dailyReportResponseDTO.setCreatedAt(dailyReport.getCreatedAt());
        dailyReportResponseDTO.setUpdatedAt(dailyReport.getUpdatedAt());

        Set<CBReportDTO> cbReportSet = new HashSet<>();
        for (CBReport cbReport : dailyReport.getCbReports()) {
            CBReport r = new CBReport();
            r.setVal(cbReport.getVal());
            r.setCbElement(cbReport.getCbElement());
            cbReportSet.add(cbReportMapper.toDTO(cbReport));
        }

        dailyReportResponseDTO.setReports(cbReportSet);

        return dailyReportResponseDTO;
    }

    @Override
    public List<DailyReportDTO> getAllReports() {
        List<DailyReport> reports = reportRepository.findAll();
        return List.of();
    }

}
