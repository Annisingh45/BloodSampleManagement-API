package com.ketan.bsm.repository;

import com.ketan.bsm.entity.DonationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRequestRepository extends JpaRepository<DonationRequest,Integer> {
    List<DonationRequest> findByRequestCompleted(boolean s);
}
