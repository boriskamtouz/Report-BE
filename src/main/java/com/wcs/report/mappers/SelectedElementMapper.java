package com.wcs.report.mappers;

import com.wcs.report.entities.CBElement;
import com.wcs.report.entities.SelectedCBElement;
import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.SelectedCBElementDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface SelectedElementMapper {
    @Mapping(target = "cbElements", source = "cbElementDTOS")
    SelectedCBElement toEntity(SelectedCBElementDTO dto);

    @Mapping(target = "cbElementDTOS", source = "cbElements")
    SelectedCBElementDTO toDTO(SelectedCBElement entity);

    Set<CBElement> toCBElementList(Set<CBElementDTO> dtos);
    Set<CBElementDTO> toCBElementDTOList(Set<CBElement> entities);

    CBElement toEntity(CBElementDTO dto);

    @InheritInverseConfiguration
    CBElementDTO toDTO(CBElement entity);
}
