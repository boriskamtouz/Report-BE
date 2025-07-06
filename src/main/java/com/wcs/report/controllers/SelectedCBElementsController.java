package com.wcs.report.controllers;

import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.SelectedCBElementDTO;
import com.wcs.report.services.SelectedCBElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class SelectedCBElementsController {
    private final SelectedCBElementService selectedCBElementService;

    @Autowired
    public SelectedCBElementsController(SelectedCBElementService selectedCBElementService) {
        this.selectedCBElementService = selectedCBElementService;
    }

    @GetMapping("{userId}/selectedElements")
    public ResponseEntity<SelectedCBElementDTO> getSelectedElementsForUser(@PathVariable(name = "userId") Long userId) {
        return new ResponseEntity<>(selectedCBElementService.getSelectedCBElementForUser(userId), HttpStatus.OK);
    }

    @PostMapping("{userId}/selectedElements")
    public ResponseEntity<SelectedCBElementDTO> create(
            @RequestBody SelectedCBElementDTO selectedCBElementDTO,
            @PathVariable(name = "userId") Long userId
    ) {
        return new ResponseEntity<>(selectedCBElementService.create(selectedCBElementDTO, userId), HttpStatus.CREATED);
    }

    @GetMapping("selectedElements")
    public ResponseEntity<SelectedCBElementDTO> getAllSelectedCBElementsForUser() {
        return ResponseEntity.ok(selectedCBElementService.getAllSelectedCBElementsForUser());
    }

    @PutMapping("{userId}/selectedElements")
    public ResponseEntity<SelectedCBElementDTO> updateSelectedCBElements(
            @RequestBody CBElementDTO cbElementDTO,
            @PathVariable(name = "userId") Long userId
    ) {
        return new ResponseEntity<>(selectedCBElementService.updateSelectedCBElements(cbElementDTO, userId), HttpStatus.OK);
    }

    @DeleteMapping("{userId}/selectedElements")
    public ResponseEntity<SelectedCBElementDTO> deleteSelectedCBElements(
            @RequestBody CBElementDTO cbElementDTO,
            @PathVariable(name = "userId") Long userId
    ) {
        return new ResponseEntity<>(selectedCBElementService.removeCBElement(cbElementDTO, userId), HttpStatus.OK);
    }
}
