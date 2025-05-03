package com.ketan.bsm.exception;

public class SurveyNotFoundByIdException extends RuntimeException {
    private final String message;

    public SurveyNotFoundByIdException(String  message) {

        this.message=message;
    }
    @Override
    public String getMessage(){
        return message;
    }
}
