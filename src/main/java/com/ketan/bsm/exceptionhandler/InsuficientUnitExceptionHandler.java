package com.ketan.bsm.exceptionhandler;

import com.ketan.bsm.exception.InsuficientUnitException;
import com.ketan.bsm.utility.ErrorStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class InsuficientUnitExceptionHandler {
    private final RestResponseBuilder restResponseBuilder;
    @ExceptionHandler
    public ResponseEntity<ErrorStructure<String>> handelInsufficientUnit(InsuficientUnitException ex){
        return restResponseBuilder.error(HttpStatus.NOT_FOUND,ex.getMessage(),"Not Available");
    }
}

