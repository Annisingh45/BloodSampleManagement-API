package com.ketan.bsm.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


public class BloodBankNotFoundByIdException extends RuntimeException {

    private final String message;

    public BloodBankNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }

}
