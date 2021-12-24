package com.freightmate.consignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrierAccountDto {

    private String carrierName;

    private String accountNumber;

    private Integer digits;

    private Integer lastUsedIndex;

    private Integer rangeStart;

    private Integer rangeEnd;
}
