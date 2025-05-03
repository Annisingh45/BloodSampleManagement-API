package com.ketan.bsm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    private String addressLine;
    private String landMark;
    private String area;
    private String city;
    private String state;
    private String country;
    private int pinCode;

}
