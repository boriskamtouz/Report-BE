package com.wcs.report.services.impl;

import com.wcs.report.entities.SelectedCBElement;
import com.wcs.report.entities.User;
import com.wcs.report.mappers.SelectedElementMapper;
import com.wcs.report.payload.SelectedCBElementDTO;
import com.wcs.report.repository.SelectedCBElementRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.SelectedCBElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedCBElementServiceImpl implements SelectedCBElementService {

    private final SelectedCBElementRepository selectedCBElementRepository;
    private final UserRepository userRepository;
    private final SelectedElementMapper selectedElementMapper;

    @Autowired
    public SelectedCBElementServiceImpl(
            SelectedCBElementRepository selectedCBElementRepository,
            UserRepository userRepository,
            SelectedElementMapper selectedElementMapper
    ) {
        this.selectedCBElementRepository = selectedCBElementRepository;
        this.userRepository = userRepository;
        this.selectedElementMapper = selectedElementMapper;
    }

    @Override
    public SelectedCBElementDTO create(SelectedCBElementDTO selectedCBElementDTO, Long userId) {

        User foundUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        SelectedCBElement selectedCBElement = new SelectedCBElement();

        selectedCBElement.setUserId(userId);
        selectedCBElement.setCreatedAt(selectedCBElementDTO.getCreatedAt());
        selectedCBElement.setUpdatedAt(selectedCBElementDTO.getUpdatedAt());
        selectedCBElement.setCbElements(selectedElementMapper.toCBElementList(selectedCBElementDTO.getCbElementDTOS()));

        SelectedCBElement createdSelectedCBElements = selectedCBElementRepository.save(selectedCBElement);

        return selectedElementMapper.toDTO(createdSelectedCBElements);
    }

    @Override
    public SelectedCBElementDTO getSelectedCBElements() {
        List<SelectedCBElement> selectedCBElements = selectedCBElementRepository.findAll();
        SelectedCBElement selectedCBElement = selectedCBElements.stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Aucun élément trouvé"));
        return selectedElementMapper.toDTO(selectedCBElement);
    }
}
