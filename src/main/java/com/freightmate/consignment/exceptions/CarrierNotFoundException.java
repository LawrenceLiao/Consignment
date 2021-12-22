package com.freightmate.consignment.exceptions;

public class CarrierNotFoundException extends RuntimeException {
    public CarrierNotFoundException(String msg) {
        super(msg);
    }
}
