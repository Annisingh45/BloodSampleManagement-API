package com.ketan.bsm.controller;

import com.ketan.bsm.request.AddressRequest;
import com.ketan.bsm.response.AddressResponse;
import com.ketan.bsm.service.AddressService;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@AllArgsConstructor
public class AddressController {

    private final AddressService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/addresses/user")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerUserAddress(@RequestBody @Valid AddressRequest AddressRequest) throws Exception {
        AddressResponse a=service.registerUserAddress(AddressRequest);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

    @PostMapping("/addresses/hospital")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerHospitalAddress(@RequestBody @Valid AddressRequest AddressRequest,int hospitalId){
        AddressResponse a=service.registerHospitalAddress(AddressRequest,hospitalId);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

    @PostMapping("/addresses/bloodBank")
    public ResponseEntity<ResponseStructure<AddressResponse>> registerBloodBankAddress(@RequestBody AddressRequest AddressRequest,int bloodBankId){
        AddressResponse a=service.registerBllodBankAddress(AddressRequest,bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED,"Address Created",a);
    }

}
