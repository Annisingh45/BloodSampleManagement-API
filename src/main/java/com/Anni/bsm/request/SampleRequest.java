package com.ketan.bsm.request;

import com.ketan.bsm.enums.BloodGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SampleRequest {

    private BloodGroup bloodGroup;
    private int quantity;

}
