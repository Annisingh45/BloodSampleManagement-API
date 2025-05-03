package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.DonationRequest;
import com.ketan.bsm.enums.OrganizationType;
import com.ketan.bsm.exception.BloodBankNotFoundByIdException;
import com.ketan.bsm.exception.DonationRequestNotFoundByIdException;
import com.ketan.bsm.exception.DonationRequestNotFoundException;
import com.ketan.bsm.exception.HospitalNotFoundByIdException;
import com.ketan.bsm.repository.BloodBankRepository;
import com.ketan.bsm.repository.DonationRequestRepository;
import com.ketan.bsm.repository.HospitalRepository;
import com.ketan.bsm.request.DonationRequestRequest;
import com.ketan.bsm.response.DonationRequestResponse;
import com.ketan.bsm.service.DonationRequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DonationRequestServiceImplementation implements DonationRequestService {

    private final DonationRequestRepository donationRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;

    private DonationRequestResponse mapToResponse(DonationRequest donationRequest) {
        return DonationRequestResponse.builder()
                .requestId(donationRequest.getRequestId())
                .bloodGroups(donationRequest.getBloodGroups())
                .cites(donationRequest.getCites())
                .build();

    }

    private DonationRequest mapToEntity(DonationRequestRequest request, DonationRequest donationRequest) {
        donationRequest.setCites(request.getCites());
        donationRequest.setBloodGroups(request.getBloodGroups());
        return donationRequest;
    }

    @Override
    public DonationRequestResponse registerDonationWithHospital(DonationRequestRequest request, int hospitalId) {
        var hospital = hospitalRepository.findById(hospitalId)
                .orElseThrow(() -> new HospitalNotFoundByIdException("Hospital not found with ID: " + hospitalId));

        DonationRequest donationRequest = mapToEntity(request, new DonationRequest());
        donationRequest.setType(OrganizationType.HOSPITAL);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse registerDonationWithBloodBank(DonationRequestRequest request, int bloodBankId) {
        var bloodBank = bloodBankRepository.findById(bloodBankId)
                .orElseThrow(() -> new BloodBankNotFoundByIdException("Blood bank not found with ID: " + bloodBankId));
        var donationRequest = mapToEntity(request, new DonationRequest());
        donationRequest.setType(OrganizationType.BLOODBANK);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse updateDonationById(DonationRequestRequest request, int donationId) {
        var donationRequest = donationRequestRepository.findById(donationId)
                .orElseThrow(() -> new DonationRequestNotFoundException("Donation request not found with ID: " + donationId));
        mapToEntity(request, donationRequest);
        donationRequestRepository.save(donationRequest);
        return mapToResponse(donationRequest);
    }

    @Override
    public DonationRequestResponse findDonationById(int donationId) {
        var donationRequest = donationRequestRepository.findById(donationId)
                .orElseThrow(() -> new DonationRequestNotFoundException("Donation request not found with ID: " + donationId));
        return mapToResponse(donationRequest);
    }
}