package com.ketan.bsm.request;

import com.ketan.bsm.enums.TransactionType;
import com.ketan.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
    private int noOfUnits;
    private TransactionType transactionType;
    private BloodGroup bloodGroup;
}
