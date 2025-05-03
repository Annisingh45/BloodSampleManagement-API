package com.ketan.bsm.service;

import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.request.BloodBankRequest;
import com.ketan.bsm.response.BloodBankPageResponse;
import com.ketan.bsm.response.BloodBankResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BloodBankService {

    public BloodBankResponse registerBloodBank(BloodBankRequest BloodBank,int adminId);

    public Page<BloodBank> findAllBloodBanks(List<String> locations, BloodGroup bloodGroup, int page, int size);

    public BloodBankResponse findBloodBankById(int BloodBankId);

    public BloodBankResponse deleteBloodBankById(int BloodBankId);

    public BloodBankResponse updateBloodBankById(BloodBankRequest BloodBankRequest,int BloodBankId);

    public List<BloodBankPageResponse> generateBloodBankPageResponse(Page<BloodBank> bloodBanks,BloodGroup bloodGroup);


}
