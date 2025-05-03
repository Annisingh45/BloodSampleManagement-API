package com.ketan.bsm.service;

import com.ketan.bsm.entity.DonationRequest;
import com.ketan.bsm.request.DonationRequestRequest;
import com.ketan.bsm.response.DonationRequestResponse;

import java.util.List;

public interface DonationRequestService {


    DonationRequestResponse registerDonationWithHospital(DonationRequestRequest donationRequest, int hospitalId);


    DonationRequestResponse registerDonationWithBloodBank(DonationRequestRequest donationRequest, int bloodBankId);

    DonationRequestResponse updateDonationById(DonationRequestRequest donationRequest, int donationId);

    DonationRequestResponse findDonationById(int donationId);
}
