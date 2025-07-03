package com.wcs.report.services;

import com.wcs.report.payload.SelectedCBElementDTO;
import org.springframework.stereotype.Service;

@Service
public interface SelectedCBElementService {
    SelectedCBElementDTO create(SelectedCBElementDTO selectedCBElementDTO, Long userId);
    SelectedCBElementDTO getSelectedCBElements();
}
