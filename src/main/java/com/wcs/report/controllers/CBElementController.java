package com.wcs.report.controllers;

import com.wcs.report.payload.CBElementDTO;
import com.wcs.report.services.CBElementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cbelements")
public class CBElementController {
    private final CBElementService cbElementService;

    public CBElementController(CBElementService cbElementService) {
        this.cbElementService = cbElementService;
    }

    @PostMapping
    public ResponseEntity<CBElementDTO> createCBElement(@RequestBody CBElementDTO cbElementDTO) {
        return new ResponseEntity<>(cbElementService.createCBElement(cbElementDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CBElementDTO>> getAllCBElements() {
        return new ResponseEntity<>(cbElementService.getAllCBElements(), HttpStatus.OK);
    }
}
