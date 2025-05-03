package com.ketan.bsm.service;

import com.ketan.bsm.entity.Address;
import com.ketan.bsm.request.AddressRequest;
import com.ketan.bsm.response.AddressResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface AddressService {

    AddressResponse registerUserAddress(@Valid AddressRequest addressRequest) throws Exception;

    AddressResponse registerHospitalAddress(@Valid AddressRequest addressRequest,int hospitalId);

    AddressResponse registerBllodBankAddress(@Valid AddressRequest addressRequest,int bloodBankId);
}
