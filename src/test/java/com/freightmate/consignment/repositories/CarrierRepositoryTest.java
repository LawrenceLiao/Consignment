package com.freightmate.consignment.repositories;

import com.freightmate.consignment.models.Carrier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.OffsetDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles(value = "test")
class CarrierRepositoryTest {

    @Autowired
    private CarrierRepository carrierRepository;

    private Long generatedId = null;

    @BeforeEach
    void setUp() {
        generatedId = carrierRepository.save(Carrier.builder()
                .name("thisistestcarrier")
                .prefix("TITC")
                .createdDateTime(OffsetDateTime.now())
                .updatedDateTime(OffsetDateTime.now())
                .build()).getId();
    }

    @AfterEach
    void tearDown() {
        carrierRepository.deleteById(generatedId);
    }

    @Test
    void shouldReturnCarrierAsPerCarrierNameGiven() {
        String carrierName = "thisistestcarrier";

        Optional<Carrier> carrier = carrierRepository.findByName(carrierName);

        assertTrue(carrier.isPresent());

        String expectedPrefix = "TITC";

        assertEquals(expectedPrefix, carrier.get().getPrefix());

    }

}