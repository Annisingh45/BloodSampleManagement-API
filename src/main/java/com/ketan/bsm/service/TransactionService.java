package com.ketan.bsm.service;

import com.ketan.bsm.request.TransactionRequest;
import com.ketan.bsm.response.TransactionResponse;
import com.ketan.bsm.response.UserResponse;

import java.util.List;

public interface TransactionService {


    public TransactionResponse registerUser(TransactionRequest transactionRequest,int hospitalId,int userId) throws Exception;
}
