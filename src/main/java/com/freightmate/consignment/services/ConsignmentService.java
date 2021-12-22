package com.freightmate.consignment.services;

import com.freightmate.consignment.dtos.CarrierAccountDto;
import com.freightmate.consignment.exceptions.CarrierNotFoundException;
import com.freightmate.consignment.exceptions.IndexInvalidException;
import com.freightmate.consignment.models.Carrier;
import com.freightmate.consignment.repositories.CarrierRepository;
import com.freightmate.consignment.utils.ConsignmentIndexFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsignmentService {

    private final CarrierRepository carrierRepository;

    private final ConsignmentIndexFormatter formatter;


    public String generateConnoteNumber(CarrierAccountDto carrierAccountDto) {
        if(!isIndexValid(carrierAccountDto.getLastUsedIndex(), carrierAccountDto.getRangeStart(), carrierAccountDto.getRangeEnd())) {
            throw new IndexInvalidException( "The index is invalid, please check the range and re-enter" );
        }
        Integer currentIndex = carrierAccountDto.getLastUsedIndex() + 1;

        Carrier carrier = carrierRepository.findByName(carrierAccountDto.getCarrierName())
                .orElseThrow( () -> new CarrierNotFoundException("No carrier with the name given found in database"));

        String prefix = carrier.getPrefix();
        String accountNumber = carrierAccountDto.getAccountNumber();
        List<Integer> listOfFormattedIndex = formatter.formattingIndexAsPerDigits(currentIndex, carrierAccountDto.getDigits());
    }

    private Boolean isIndexValid(Integer lastIndex, Integer rangeStart, Integer rangeEnd) {
        return lastIndex < rangeStart || lastIndex >= rangeEnd;
    }
}
