package com.freightmate.consignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarrierAccountDto {

    @NotBlank
    private String carrierName;

    @NotBlank
    private String accountNumber;

    private Integer digits;

    private Integer lastUsedIndex;

    private Integer rangeStart;

    private Integer rangeEnd;
}
