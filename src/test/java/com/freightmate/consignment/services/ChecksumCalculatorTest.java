package com.freightmate.consignment.services;

import com.freightmate.consignment.services.calculator.ChecksumCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class ChecksumCalculatorTest {

    private ChecksumCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ChecksumCalculator();
    }

    @Test
    void shouldReturnCorrectChecksumResult() {
        List<Integer> list = List.of(0,0,0,0,0,1,9,9,9,9);

        Integer checksum = calculator.calculateChecksum(list);

        Integer expectedChecksum = 7;

        assertEquals(expectedChecksum, checksum);
    }
}