package com.ketan.bsm.exception;

public class InsuficientUnitException extends RuntimeException {
    private final String message;

    public InsuficientUnitException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}