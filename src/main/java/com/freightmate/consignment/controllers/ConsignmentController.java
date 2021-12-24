package com.freightmate.consignment.controllers;

import com.freightmate.consignment.dtos.CarrierAccountDto;
import com.freightmate.consignment.services.ConsignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/consignment")
public class ConsignmentController {

    private final ConsignmentService consignmentService;

    @PostMapping("/connote-number")
    public ResponseEntity<String> getConnoteNumber(@RequestBody CarrierAccountDto carrierAccount) {
        String outputConnoteNumber = consignmentService.generateConnoteNumber(carrierAccount);
        return ResponseEntity.ok(outputConnoteNumber);
    }
}
