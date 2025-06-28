package com.wcs.report.services.impl;

import com.wcs.report.entities.CBElement;
import com.wcs.report.entities.Report;
import com.wcs.report.entities.User;
import com.wcs.report.payload.ReportDTO;
import com.wcs.report.repository.ReportRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ReportDTO createReport(ReportDTO reportDTO) {

        User user = userRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Report report = new Report();
        report.setCreatedAt(reportDTO.getCreatedAt());
        report.setUpdatedAt(reportDTO.getUpdatedAt());
        report.setUser(user);

        List<CBElement> cbElements = reportDTO.getCbElementDTOS()
                .stream().map(
                        cbElementDTO -> {
                            CBElement cbElement = new CBElement();
                            cbElement.setName(cbElementDTO.getName());
                            cbElement.setVal(cbElementDTO.getVal());
                            cbElement.setReport(report);
                            return cbElement;
                        }
                ).toList();

        report.setCbElements(cbElements);

        Report savedReport = reportRepository.save(report);

        return new ReportDTO().toDTO(savedReport);
    }

}
