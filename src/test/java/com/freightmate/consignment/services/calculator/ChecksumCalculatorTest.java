package com.freightmate.consignment.services.calculator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
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