package com.ketan.bsm.controller;

import com.ketan.bsm.request.HospitalRequest;
import com.ketan.bsm.response.HospitalResponse;
import com.ketan.bsm.service.HospitalService;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@AllArgsConstructor
public class HospitalController {


    private final HospitalService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/hospitals")
    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    public ResponseEntity<ResponseStructure<HospitalResponse>> registerHospital(@RequestBody @Valid HospitalRequest HospitalRequest,int adminId){
        HospitalResponse a=service.registerHospital(HospitalRequest,adminId);
        return responseBuilder.success(HttpStatus.CREATED,"Hospital Created",a);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @GetMapping("/Hospitals")
    public ResponseEntity<ResponseStructure<List<HospitalResponse>>> findAllHospitals(){
        List<HospitalResponse> l= service.findAllHospitals();
        return responseBuilder.success(HttpStatus.FOUND,"Hospital Found",l);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN')")
    @PutMapping("/hospitals")
    public ResponseEntity<ResponseStructure<HospitalResponse>> updateHospitalById(@RequestBody @Valid HospitalRequest HospitalRequest,
                                                                                  int HospitalId) {
        HospitalResponse u=service.updateHospitalById(HospitalRequest,HospitalId);
        return responseBuilder.success(HttpStatus.FOUND,"Hospital Updated",u);
    }

    @DeleteMapping("/hospitals")
    public ResponseEntity<ResponseStructure<HospitalResponse>> deleteHospitalById(@RequestParam int HospitalId){
        HospitalResponse d=service.deleteHospitalById(HospitalId);
        return responseBuilder.success(HttpStatus.FOUND,"Hospital Deleted",d);
    }

    @GetMapping("/hospital")
    public ResponseEntity<ResponseStructure<HospitalResponse>> findHospitalById(@RequestParam int HospitalId){
        HospitalResponse f=service.findHospitalById(HospitalId);
        return responseBuilder.success(HttpStatus.FOUND,"Hospital Found",f);
    }



}
