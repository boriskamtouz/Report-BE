package com.wcs.report.controllers;

import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.payload.SelectedCBElementDTO;
import com.wcs.report.services.SelectedCBElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selectedElements")
public class SelectedElementController {
    private final SelectedCBElementService selectedCBElementService;

    @Autowired
    public SelectedElementController(SelectedCBElementService selectedCBElementService) {
        this.selectedCBElementService = selectedCBElementService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<SelectedCBElementDTO> create(
            @RequestBody SelectedCBElementDTO selectedCBElementDTO,
         @PathVariable(name = "userId")   Long userId
    ) {
        return new ResponseEntity<>(selectedCBElementService.create(selectedCBElementDTO, userId), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<SelectedCBElementDTO> getSelectedCBElements() {
        return ResponseEntity.ok(selectedCBElementService.getSelectedCBElements());
    }
}
