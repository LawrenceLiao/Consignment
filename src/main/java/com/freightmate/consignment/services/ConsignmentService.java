package com.freightmate.consignment.services;

import com.freightmate.consignment.dtos.CarrierAccountDto;
import com.freightmate.consignment.exceptions.CarrierNotFoundException;
import com.freightmate.consignment.exceptions.IndexInvalidException;
import com.freightmate.consignment.models.Carrier;
import com.freightmate.consignment.repositories.CarrierRepository;
import com.freightmate.consignment.services.calculator.ChecksumCalculator;
import com.freightmate.consignment.utils.ConsignmentIndexFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsignmentService {

    private final CarrierRepository carrierRepository;

    private final ChecksumCalculator calculator;

    private final ConsignmentIndexFormatter formatter;


    public String generateConnoteNumber(CarrierAccountDto carrierAccountDto) {
        if(!isIndexValid(carrierAccountDto.getLastUsedIndex(), carrierAccountDto.getRangeStart(), carrierAccountDto.getRangeEnd())) {
            throw new IndexInvalidException( "The index is invalid, please check the range and re-enter" );
        }
        Integer currentIndex = Math.incrementExact(carrierAccountDto.getLastUsedIndex());

        log.info("Tht current index is {}", currentIndex);

        Carrier carrier = carrierRepository.findByName(carrierAccountDto.getCarrierName().toLowerCase(Locale.ROOT))
                .orElseThrow( () -> new CarrierNotFoundException("No carrier with the name given found in database"));

        log.info("Operating carrier's ID is {} and name is {}", carrier.getId(), carrier.getName() );

        String prefix = carrier.getPrefix();
        String accountNumber = carrierAccountDto.getAccountNumber();
        List<Integer> listOfFormattedIndex = formatter.formattingIndexAsPerDigits(currentIndex, carrierAccountDto.getDigits());

        Integer checkSum = calculator.calculateChecksum(listOfFormattedIndex);

        List<String> outputElements = List.of(prefix, accountNumber,
                formatter.mapIndexListToString(listOfFormattedIndex), checkSum.toString());

        String connoteNumber = formatter.mapStringListToString(outputElements);

        log.info("Generated connote number is {}", connoteNumber);
        return connoteNumber;
    }

    private Boolean isIndexValid(Integer lastIndex, Integer rangeStart, Integer rangeEnd) {
        return lastIndex >= rangeStart && lastIndex < rangeEnd;
    }

}
