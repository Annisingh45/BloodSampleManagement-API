package com.ketan.bsm.exception;

public class DonationRequestNotFoundException extends RuntimeException{
    private final String message;

    public DonationRequestNotFoundException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}