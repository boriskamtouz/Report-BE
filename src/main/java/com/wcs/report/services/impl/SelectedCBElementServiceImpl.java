package com.wcs.report.services.impl;

import com.wcs.report.entities.CBElement;
import com.wcs.report.entities.SelectedCBElement;
import com.wcs.report.entities.User;
import com.wcs.report.mappers.SelectedElementMapper;
import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.SelectedCBElementDTO;
import com.wcs.report.repository.CBElementRepository;
import com.wcs.report.repository.SelectedCBElementRepository;
import com.wcs.report.repository.UserRepository;
import com.wcs.report.services.SelectedCBElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SelectedCBElementServiceImpl implements SelectedCBElementService {

    private final SelectedCBElementRepository selectedCBElementRepository;
    private final CBElementRepository cbElementRepository;
    private final UserRepository userRepository;
    private final SelectedElementMapper selectedElementMapper;

    @Autowired
    public SelectedCBElementServiceImpl(
            SelectedCBElementRepository selectedCBElementRepository, CBElementRepository cbElementRepository,
            UserRepository userRepository,
            SelectedElementMapper selectedElementMapper
    ) {
        this.selectedCBElementRepository = selectedCBElementRepository;
        this.cbElementRepository = cbElementRepository;
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
    public SelectedCBElementDTO updateSelectedCBElements(CBElementDTO cbElementDTO, Long userId) {
        User foundedUser = checkIfUserExist(userId);

        SelectedCBElement foundedSelectedCBElements = checkIfSelectedCBElementExistForUser(userId);

        Set<CBElement> userCBElements = foundedSelectedCBElements.getCbElements();


        if (cbElementRepository.existsByCode(cbElementDTO.getCode())) {
            CBElement cbElementToAdd = cbElementRepository.findByCodeIgnoreCase(cbElementDTO.getCode());
            userCBElements.add(cbElementToAdd);
        } else {
            throw new RuntimeException("There is no element with code: " + cbElementDTO.getCode());
        }

        foundedSelectedCBElements.setUpdatedAt(LocalDateTime.now());

        selectedCBElementRepository.save(foundedSelectedCBElements);

        return selectedElementMapper.toDTO(foundedSelectedCBElements);
    }

    @Override
    public SelectedCBElementDTO removeCBElement(CBElementDTO cbElementDTO, Long userId) {
        User foundedUser = checkIfUserExist(userId);

        SelectedCBElement foundedSelectedCBElements = checkIfSelectedCBElementExistForUser(userId);

        Set<CBElement> userCBElements = foundedSelectedCBElements.getCbElements();
        CBElement cbElement = selectedElementMapper.toEntity(cbElementDTO);

        userCBElements.removeIf(cbE -> cbE.getId().equals(cbElement.getId()));

        selectedCBElementRepository.save(foundedSelectedCBElements);

        return selectedElementMapper.toDTO(foundedSelectedCBElements);

    }

    @Override
    public SelectedCBElementDTO getAllSelectedCBElementsForUser() {
        List<SelectedCBElement> selectedCBElements = selectedCBElementRepository.findAll();
        SelectedCBElement selectedCBElement = selectedCBElements.stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Aucun élément trouvé"));
        return selectedElementMapper.toDTO(selectedCBElement);
    }

    @Override
    public SelectedCBElementDTO getSelectedCBElementForUser(Long userId) {
        User user = checkIfUserExist(userId);
        SelectedCBElement selectedCBElement = checkIfSelectedCBElementExistForUser(userId);
        return selectedElementMapper.toDTO(selectedCBElement);
    }


    public User checkIfUserExist(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    public SelectedCBElement checkIfSelectedCBElementExistForUser(Long userId) {
        SelectedCBElement foundedSelectedCBElements = selectedCBElementRepository.findByUserId(userId);

        if (foundedSelectedCBElements == null) {
            throw new RuntimeException("There is no elements for user id: " + userId);
        }

        return foundedSelectedCBElements;
    }
}
