package com.freightmate.consignment.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freightmate.consignment.dtos.CarrierAccountDto;
import com.freightmate.consignment.exceptions.CarrierNotFoundException;
import com.freightmate.consignment.exceptions.IndexInvalidException;
import com.freightmate.consignment.services.ConsignmentService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ConsignmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ConsignmentService consignmentService;

    private CarrierAccountDto accountDto;


    @BeforeEach
    void setUp() {
        accountDto = CarrierAccountDto.builder()
                .carrierName("ThisIsAnotherTestCarrier")
                .accountNumber("456EFG")
                .digits(10)
                .lastUsedIndex(11000)
                .rangeStart(10000)
                .rangeEnd(20000)
                .build();
    }

    @AfterEach
    void tearDown() {
        accountDto = null;
    }

    @Test
    void shouldReturnGeneratedNumberWithOkStatus() throws Exception {

        String expectedConnoteNumber = "TEST456EFG12345678901";

        ArgumentCaptor<CarrierAccountDto> captor = ArgumentCaptor.forClass(CarrierAccountDto.class);

        when(consignmentService.generateConnoteNumber(captor.capture())).thenReturn(expectedConnoteNumber);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/consignment/connote-number")
                        .content(objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedConnoteNumber));

        assertEquals(accountDto, captor.getValue());

    }

    @Test
    void shouldReturnCarrierNotFoundErrorWithNotFoundHttpStatus() throws Exception {

        when(consignmentService.generateConnoteNumber(any())).thenThrow(CarrierNotFoundException.class);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/consignment/connote-number")
                        .content(objectMapper.writeValueAsString(accountDto))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("CARRIER_NOT_FOUND"))
                .andExpect(jsonPath("$.details").value("Please check the carrier name"));

    }

    @Test
    void shouldReturnIndexInvalidErrorWithBadRequestHttpStatus() throws Exception {

        when(consignmentService.generateConnoteNumber(any())).thenThrow(IndexInvalidException.class);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/consignment/connote-number")
                                .content(objectMapper.writeValueAsString(accountDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("INVALID_INDEX"))
                .andExpect(jsonPath("$.details").value("Please check the range given"));
    }

    @Test
    void shouldThrowServiceUnavailableErrorWhenExceptionsWithoutExplicitHandlingOccurred() throws Exception {

        when(consignmentService.generateConnoteNumber(any())).thenThrow(IllegalAccessError.class);

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/consignment/connote-number")
                                .content(objectMapper.writeValueAsString(accountDto))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isServiceUnavailable())
                .andExpect(jsonPath("$.message").value("ERROR_OCCURRED_IN_SERVER_SIDE"))
                .andExpect(jsonPath("$.details").value("Please contact admin"));
    }
}