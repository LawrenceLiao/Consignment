package com.freightmate.consignment.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConsignmentErrorDto {
    private String message;
    private String details;
}
