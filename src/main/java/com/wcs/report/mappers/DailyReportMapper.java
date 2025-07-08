package com.wcs.report.mappers;

import com.wcs.report.entities.CBReport;
import com.wcs.report.entities.DailyReport;
import com.wcs.report.payload.CBReportDTO;
import com.wcs.report.payload.DailyReportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DailyReportMapper {
    DailyReportMapper INSTANCE = Mappers.getMapper(DailyReportMapper.class);

    DailyReport toDailyReportEntity(DailyReportDTO dailyReportDTO);

    DailyReportDTO toDailyReportDTO(DailyReport dailyReport);

    Set<CBReportDTO> toCBReportDTOs(Set<CBReport> cbReports);

    Set<DailyReport> toDailyReportEntitySet(Set<DailyReportDTO> dailyReportDTOs);
}
