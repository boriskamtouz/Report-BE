package com.wcs.report.mappers;

import com.wcs.report.entities.SelectedCBElement;
import com.wcs.report.entities.User;
import com.wcs.report.payload.SelectedCBElementDTO;
import com.wcs.report.payload.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "selectedCBElementDTO", source = "selectedCBElement")
    UserDTO toDTO(User user);

    @Mapping(target = "selectedCBElement", source = "selectedCBElementDTO")
    User toEntity(UserDTO userDTO);

    @Mapping(target = "cbElements", source = "cbElementDTOS")
    SelectedCBElement toSelectedCBElementEntity(SelectedCBElementDTO selectedCBElementDTO);

    @Mapping(target = "cbElementDTOS", source = "cbElements")
    SelectedCBElementDTO toSelectedCBElementDTO(SelectedCBElement selectedCBElement);
}
