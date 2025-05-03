package com.ketan.bsm.repository;

import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.entity.DonationRequest;
import com.ketan.bsm.entity.Sample;
import com.ketan.bsm.enums.BloodGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BloodBankRepository extends JpaRepository<BloodBank,Integer> {


 Page<BloodBank> findByAddress_CityInAndSamples_BloodGroupIn(List<String> city, List<BloodGroup> bloodGroups, Pageable pageable);

    Optional<BloodBank> findByDonationRequests(DonationRequest donationRequest);
// public List<BloodBank> findBySample_BloodGroup(BloodGroup bloodGroup);

// List<Sample> findAllWithSamples();
}
