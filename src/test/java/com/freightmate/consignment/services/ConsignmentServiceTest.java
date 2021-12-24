package com.freightmate.consignment.services;

import com.freightmate.consignment.dtos.CarrierAccountDto;
import com.freightmate.consignment.exceptions.CarrierNotFoundException;
import com.freightmate.consignment.exceptions.IndexInvalidException;
import com.freightmate.consignment.models.Carrier;
import com.freightmate.consignment.repositories.CarrierRepository;
import com.freightmate.consignment.services.calculator.ChecksumCalculator;
import com.freightmate.consignment.utils.ConsignmentIndexFormatter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@SpringBootTest
class ConsignmentServiceTest {

    @Mock
    private CarrierRepository carrierRepository;

    @Mock
    private ChecksumCalculator calculator;

    @Autowired
    private ConsignmentIndexFormatter formatter;

    private ConsignmentService consignmentService;

    private CarrierAccountDto accountDto;

    @BeforeEach
    void setUp() {
        accountDto = CarrierAccountDto.builder()
                .carrierName("ThisIsTestCarrier")
                .accountNumber("456EFG")
                .digits(10)
                .lastUsedIndex(11000)
                .rangeStart(10000)
                .rangeEnd(20000)
                .build();

        consignmentService = new ConsignmentService(carrierRepository, calculator, formatter);
    }

    @AfterEach
    void tearDown() {
        accountDto = null;
    }

    @Test
    void shouldThrowCarrierNotFoundExceptionWhenNoRelatedDataFoundInDB() {
        when(carrierRepository.findByName(anyString())).thenReturn(Optional.ofNullable(null));

        assertThrows(CarrierNotFoundException.class, () -> consignmentService.generateConnoteNumber(accountDto));

    }

    @Test
    void shouldReturnIndexInvalidExceptionWhenIndexOutOfRange() {
        accountDto.setRangeStart(12000);

        assertThrows(IndexInvalidException.class, () -> consignmentService.generateConnoteNumber(accountDto));
    }

    @Test
    void shouldReturnConnoteNumberAsPerDataGiven() {
        when(carrierRepository.findByName(anyString())).thenReturn(Optional.of(Carrier.builder()
                        .id(1L)
                        .name("ThisIsTestCarrier")
                        .prefix("TITC")
                        .createdDateTime(OffsetDateTime.now())
                        .updatedDateTime(OffsetDateTime.now())
                        .build()));

        when(calculator.calculateChecksum(any())).thenReturn(1);

        ArgumentCaptor<List<Integer>> captor = ArgumentCaptor.forClass(List.class);

        String expectedNumber = "TITC456EFG00000110011";

        assertEquals(expectedNumber, consignmentService.generateConnoteNumber(accountDto));

        verify(calculator, times(1)).calculateChecksum(captor.capture());

        List<Integer> expectedList = List.of(0, 0, 0, 0, 0, 1, 1, 0, 0, 1);

        assertEquals(expectedList, captor.getValue());

    }




}

