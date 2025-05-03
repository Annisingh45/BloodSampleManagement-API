package com.ketan.bsm.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankPageResponse {
    private int bloodBankId;
    private String bloodName;
    private AddressResponse address;
    private List<SampleResponse> samples;


}
