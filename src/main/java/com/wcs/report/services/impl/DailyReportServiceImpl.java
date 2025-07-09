package com.wcs.report.services.impl;

import com.wcs.report.entities.*;
import com.wcs.report.mappers.CBReportMapper;
import com.wcs.report.mappers.CBReportUpdateDTO;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.payload.DailyReportDTO;
import com.wcs.report.payload.DailyReportResponseDTO;
import com.wcs.report.repository.DailyReportRepository;
import com.wcs.report.repository.SelectedCBElementRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.DailyReportService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DailyReportServiceImpl implements DailyReportService {
    private final DailyReportRepository reportRepository;
    private final UserRepository userRepository;
    private final CBReportMapper cbReportMapper;
    private final SelectedCBElementRepository selectedCBElementRepository;


    @Autowired
    public DailyReportServiceImpl(
            DailyReportRepository reportRepository,
            UserRepository userRepository,
            CBReportMapper cbReportMapper, SelectedCBElementRepository selectedCBElementRepository
    ) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
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

        if (userCBElements == null || userCBElements.isEmpty()) {
            throw new RuntimeException("User has not selected CB elements");
        }

        for (CBElement cbElement : userCBElements) {
            CBReport cbReport = new CBReport();
            cbReport.setVal("0"); // TODO: Voir cette valeur ne doit pas changer
            cbReport.setCbElement(cbElement);
            cbReport.setDailyReport(report);
            cbReports.add(cbReport);
        }

        report.setCbReports(cbReports);

        DailyReport dailyReport = reportRepository.save(report);

        DailyReportResponseDTO dailyReportResponseDTO = getDailyReportResponseDTO(dailyReport);

        return dailyReportResponseDTO;
    }

    @Override
    public DailyReportResponseDTO getDailyReportById(Long userId, Long dailyReportId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        DailyReport dailyReport = reportRepository.findByIdAndUser(userId, user)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));

        DailyReportResponseDTO dailyReportResponseDTO = getDailyReportResponseDTO(dailyReport);

        return dailyReportResponseDTO;
    }

    @Override
    public DailyReportResponseDTO updateDailyReport(Long userId, Long dailyReportId, List<CBReportUpdateDTO> cbReportDTOS) {
        User user = checkIfUserExist(userId);

        DailyReport foundedDailyReport = user.getReports().stream()
                .filter(dailyReport -> dailyReport.getId().equals(dailyReportId))
                .findFirst().orElseThrow(() -> new EntityNotFoundException("Daily report not found"));

        foundedDailyReport.setUpdatedAt(LocalDateTime.now());

        Set<CBReport> cbReports = foundedDailyReport.getCbReports()
                .stream().collect(Collectors.toSet());

        Map<Long, CBReport> cbReportMap = foundedDailyReport.getCbReports()
                .stream().collect(Collectors.toMap(
                        r -> r.getCbElement().getId(),
                        Function.identity()
                ));

        for (CBReportUpdateDTO cbReportDTO : cbReportDTOS) {
            CBReport cbReport = cbReportMap.get(cbReportDTO.getCbElementId());
            if (cbReport != null) {
                cbReport.setVal(cbReportDTO.getVal());
            } else {
                throw new EntityNotFoundException("CB report not found");
            }
        }

        DailyReport updatedDailyReport = reportRepository.save(foundedDailyReport);

        DailyReportResponseDTO dailyReportResponseDTO = getDailyReportResponseDTO(updatedDailyReport);

        return dailyReportResponseDTO;
    }

    @Override
    public List<DailyReportResponseDTO> getDailyReportsByUser(Long userId) {
        User user = checkIfUserExist(userId);
        List<DailyReport> dailyReports = reportRepository.findByUser(user);
        List<DailyReportResponseDTO> dailyReportResponseDTOS = new ArrayList<>();
        for (DailyReport dailyReport : dailyReports) {
            DailyReportResponseDTO dailyReportResponseDTO = getDailyReportResponseDTO(dailyReport);
            dailyReportResponseDTOS.add(dailyReportResponseDTO);
        }
        return dailyReportResponseDTOS;
    }

    private DailyReportResponseDTO getDailyReportResponseDTO(DailyReport updatedDailyReport) {
        DailyReportResponseDTO dailyReportResponseDTO = new DailyReportResponseDTO();
        dailyReportResponseDTO.setCreatedAt(updatedDailyReport.getCreatedAt());
        dailyReportResponseDTO.setUpdatedAt(updatedDailyReport.getUpdatedAt());
        Set<CBReportDTO> newCBReportsDTO = new HashSet<>();
        for (CBReport cbReport : updatedDailyReport.getCbReports()) {
            CBReport r = new CBReport();
            r.setVal(cbReport.getVal());
            r.setCbElement(cbReport.getCbElement());
            r.setDailyReport(updatedDailyReport);
            newCBReportsDTO.add(cbReportMapper.toDTO(cbReport));
        }
        dailyReportResponseDTO.setReports(newCBReportsDTO);
        return dailyReportResponseDTO;
    }


    public User checkIfUserExist(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public DailyReport checkIfDailyReportExist(Long dailyReportId) {
        return reportRepository.findById(dailyReportId)
                .orElseThrow(() -> new EntityNotFoundException("Report not found"));
    }

}
