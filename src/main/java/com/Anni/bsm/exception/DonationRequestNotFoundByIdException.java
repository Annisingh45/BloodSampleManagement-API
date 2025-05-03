package com.ketan.bsm.exception;

import com.ketan.bsm.entity.DonationRequest;

public class DonationRequestNotFoundByIdException extends RuntimeException {
    private final String message;

    public DonationRequestNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
