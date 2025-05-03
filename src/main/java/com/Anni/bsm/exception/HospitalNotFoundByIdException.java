package com.ketan.bsm.exception;

public class HospitalNotFoundByIdException extends RuntimeException {
    private final String message;

    public HospitalNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
