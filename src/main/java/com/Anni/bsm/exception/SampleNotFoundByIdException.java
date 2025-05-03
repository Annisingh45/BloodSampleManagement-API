package com.ketan.bsm.exception;

public class SampleNotFoundByIdException extends RuntimeException {
    private final String message;

    public SampleNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
