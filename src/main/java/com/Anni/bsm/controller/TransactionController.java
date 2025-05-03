package com.ketan.bsm.controller;

import com.ketan.bsm.repository.TransactionRepository;
import com.ketan.bsm.request.TransactionRequest;
import com.ketan.bsm.request.UserRequest;
import com.ketan.bsm.response.TransactionResponse;
import com.ketan.bsm.response.UserResponse;
import com.ketan.bsm.service.TransactionService;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/transactions")
    public ResponseEntity<ResponseStructure<TransactionResponse>> registerUser(@RequestBody TransactionRequest transactionRequest,int hospitalId,int userId) throws Exception {
        TransactionResponse a=transactionService.registerUser(transactionRequest,hospitalId,userId);
        return responseBuilder.success(HttpStatus.CREATED,"Transaction Success",a);
    }
}
