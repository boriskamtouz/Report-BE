package com.wcs.report.mappers;

import com.wcs.report.entities.CBElement;
import com.wcs.report.payload.CBElementDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CBElementMapper {
    CBElementMapper INSTANCE = Mappers.getMapper(CBElementMapper.class);

    CBElement toEntity(CBElementDTO dto);
    CBElementDTO toDTO(CBElement entity);
}
