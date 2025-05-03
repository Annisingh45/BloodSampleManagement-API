package com.ketan.bsm.controller;

import com.ketan.bsm.request.SampleRequest;
import com.ketan.bsm.response.SampleResponse;
import com.ketan.bsm.service.SampleService;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
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
public class SampleController {



    private final SampleService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/Samples")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    public ResponseEntity<ResponseStructure<SampleResponse>> registerSample(@RequestBody SampleRequest SampleRequest, int bloodBankId){
        SampleResponse a=service.registerSample(SampleRequest,bloodBankId);
        return responseBuilder.success(HttpStatus.CREATED,"Sample Created",a);
    }

    @GetMapping("/Samples")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    public ResponseEntity<ResponseStructure<List<SampleResponse>>> findAllSamples(){
        List<SampleResponse> l= service.findAllSamples();
        return responseBuilder.success(HttpStatus.FOUND,"Sample Found",l);
    }

    @PutMapping("/Samples")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    public ResponseEntity<ResponseStructure<SampleResponse>> updateSampleById(@RequestBody  SampleRequest SampleRequest,
                                                                              int SampleId) {
        SampleResponse u=service.updateSampleById(SampleRequest,SampleId);
        return responseBuilder.success(HttpStatus.FOUND,"Sample Updated",u);
    }

    @DeleteMapping("/Samples")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    public ResponseEntity<ResponseStructure<SampleResponse>> deleteSampleById(@RequestParam int SampleId){
        SampleResponse d=service.deleteSampleById(SampleId);
        return responseBuilder.success(HttpStatus.FOUND,"Sample Deleted",d);
    }

    @GetMapping("/Sample")
    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    public ResponseEntity<ResponseStructure<SampleResponse>> findSampleById(@RequestParam int SampleId){
        SampleResponse f=service.findSampleById(SampleId);
        return responseBuilder.success(HttpStatus.FOUND,"Sample Found",f);
    }







}
