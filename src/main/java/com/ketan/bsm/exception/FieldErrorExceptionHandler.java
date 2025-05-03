package com.ketan.bsm.exception;

import com.ketan.bsm.utility.ErrorStructure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler {
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class FieldErrorStructure{
        private String field;
        private Object rejectedValue;
        private String message;

    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<ObjectError> objectsErrors=ex.getAllErrors();
        List<FieldErrorStructure> errors=new ArrayList<>();

            for (ObjectError objectError : objectsErrors) {
                FieldError error=(FieldError) objectError;
                errors.add(FieldErrorStructure.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .rejectedValue(error.getRejectedValue())
                        .build());

            }

        ErrorStructure<List<FieldErrorStructure>> error=new ErrorStructure<>();
            error.setStatus(HttpStatus.BAD_REQUEST.value());
            error.setMessage("Invalid Data Input");
            error.setRootCause(errors);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}

