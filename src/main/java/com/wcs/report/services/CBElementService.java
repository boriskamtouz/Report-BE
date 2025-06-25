package com.wcs.report.services;

import com.wcs.report.payload.CBElementDTO;

import java.util.List;

public interface CBElementService {
    CBElementDTO createCBElement(CBElementDTO CBElementDTO);
    List<CBElementDTO> getAllCBElements();
}
