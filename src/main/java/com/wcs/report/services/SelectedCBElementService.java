package com.wcs.report.services;

import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.SelectedCBElementDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SelectedCBElementService {
    SelectedCBElementDTO create(SelectedCBElementDTO selectedCBElementDTO, Long userId);

    SelectedCBElementDTO updateSelectedCBElements(CBElementDTO cbElementDTO, Long userId);

    SelectedCBElementDTO removeCBElement(CBElementDTO cbElementDTO, Long userId);

    SelectedCBElementDTO getAllSelectedCBElementsForUser();

    SelectedCBElementDTO getSelectedCBElementForUser(Long userId);
}
