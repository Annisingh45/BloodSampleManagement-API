package com.ketan.bsm.controller;

import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.request.BloodBankRequest;
import com.ketan.bsm.response.BloodBankPageResponse;
import com.ketan.bsm.response.BloodBankResponse;
import com.ketan.bsm.service.BloodBankService;
import com.ketan.bsm.utility.PageStructure;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@AllArgsConstructor
public class BloodBankController {


    private final BloodBankService service;
    private final RestResponseBuilder responseBuilder;
    private final List<String> strings;

    @PostMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> registerBloodBank(@RequestBody @Valid BloodBankRequest BloodBankRequest,int adminId){
        BloodBankResponse a=service.registerBloodBank(BloodBankRequest,adminId);
        return responseBuilder.success(HttpStatus.CREATED,"BloodBank Created",a);
    }

    @GetMapping("/blood-banks")
    public ResponseEntity<PageStructure<List<BloodBankPageResponse>>> findAllBloodBanks(@RequestParam  List<String> locations, BloodGroup bloodGroup, int page, int size){
        Page<BloodBank> pages= service.findAllBloodBanks(locations,bloodGroup,page,size);
        List<BloodBankPageResponse> l=service.generateBloodBankPageResponse(pages,bloodGroup);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Found",l,pages.getNumber(),pages.getTotalPages(),pages.getSize());
    }

    @PutMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> updateBloodBankById(@RequestBody @Valid BloodBankRequest BloodBankRequest,
                                                                                    int BloodBankId) {
        BloodBankResponse u=service.updateBloodBankById(BloodBankRequest,BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Updated",u);
    }

    @DeleteMapping("/bloodBanks")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> deleteBloodBankById(@RequestParam int BloodBankId){
        BloodBankResponse d=service.deleteBloodBankById(BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Deleted",d);
    }

    @GetMapping("/bloodBank")
    public ResponseEntity<ResponseStructure<BloodBankResponse>> findBloodBankById(@RequestParam int BloodBankId){
        BloodBankResponse f=service.findBloodBankById(BloodBankId);
        return responseBuilder.success(HttpStatus.FOUND,"BloodBank Found",f);
    }



}
