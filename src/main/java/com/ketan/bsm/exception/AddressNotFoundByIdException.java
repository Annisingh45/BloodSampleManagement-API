package com.ketan.bsm.exception;

public class AddressNotFoundByIdException extends RuntimeException{
    private final String message;

    public AddressNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
