package com.ketan.bsm.repository;

import com.ketan.bsm.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationLeadRepository extends JpaRepository<Address,Integer> {
}
