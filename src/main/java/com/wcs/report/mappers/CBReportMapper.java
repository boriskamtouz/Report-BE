package com.wcs.report.mappers;


import com.wcs.report.entities.CBElement;
import com.wcs.report.entities.CBReport;
import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.CBReportDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CBReportMapper {
    CBReportMapper INSTANCE = Mappers.getMapper(CBReportMapper.class);


    CBReport toEntity(CBReportDTO cbReportDTO);
    CBReportDTO toDTO(CBReport cbReport);

    CBElement cbElementToEntity(CBElementDTO cbElement);
    CBElementDTO cbElementToDTO(CBElement cbElement);

    List<CBReportDTO> toCBElementsDTO(List<CBReport> cbReports);
    List<CBElement> toCBElementsEntity(List<CBElementDTO> cbElements);
}
