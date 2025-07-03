package com.wcs.report.services.impl;

import com.wcs.report.entities.CBElement;
import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.repository.CBElementRepository;
import com.wcs.report.services.CBElementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CBElementServiceImpl implements CBElementService {
    private final CBElementRepository cbElementRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CBElementServiceImpl(CBElementRepository cbElementRepository, ModelMapper modelMapper) {
        this.cbElementRepository = cbElementRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CBElementDTO createCBElement(CBElementDTO cbElementDTO) {
        CBElement cbElement = new CBElement();
        cbElement.setName(cbElementDTO.getName());
        cbElement.setCode(cbElementDTO.getCode());
        CBElement createdCBElement = cbElementRepository.save(cbElement);
        return modelMapper.map(createdCBElement, CBElementDTO.class);
    }

    @Override
    public List<CBElementDTO> getAllCBElements() {
        List<CBElement> cbElements = cbElementRepository.findAll();
        return cbElements.stream().map(
                cbElement -> modelMapper.map(cbElement, CBElementDTO.class)
        ).toList();
    }
}
