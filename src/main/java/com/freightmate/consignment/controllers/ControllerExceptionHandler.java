package com.freightmate.consignment.controllers;

import com.freightmate.consignment.dtos.ConsignmentErrorDto;
import com.freightmate.consignment.exceptions.CarrierNotFoundException;
import com.freightmate.consignment.exceptions.IndexInvalidException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(CarrierNotFoundException.class)
    public ResponseEntity<ConsignmentErrorDto> handleCarrierNotFoundException(CarrierNotFoundException exception) {
        log.info("Carrier is not found.", exception);
        return new ResponseEntity(new ConsignmentErrorDto("CARRIER_NOT_FOUND", "Please check the carrier name"),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IndexInvalidException.class)
    public ResponseEntity<ConsignmentErrorDto> handleIndexInvalidException(IndexInvalidException exception) {
        log.info("Index is invalid for the range given!", exception);
        return new ResponseEntity(new ConsignmentErrorDto("INVALID_INDEX", "Please check the range given"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ConsignmentErrorDto> handleException(Exception exception) {
        log.error("There is Exception occurred!", exception);
        return new ResponseEntity(new ConsignmentErrorDto("ERROR_OCCURRED_IN_SERVER_SIDE", "Please contact admin"),
                HttpStatus.SERVICE_UNAVAILABLE);
    }
}
