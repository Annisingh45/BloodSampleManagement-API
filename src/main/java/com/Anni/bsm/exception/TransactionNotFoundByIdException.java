package com.ketan.bsm.exception;

public class TransactionNotFoundByIdException extends RuntimeException {
    private final String message;

    public TransactionNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
