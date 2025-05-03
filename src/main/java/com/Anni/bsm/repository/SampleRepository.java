package com.ketan.bsm.repository;

import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.entity.Sample;
import com.ketan.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SampleRepository extends JpaRepository<Sample,Integer> {

    List<Sample> findByBloodBankAndBloodGroupIn(BloodBank bloodBank, List<BloodGroup> bloodGroups);
}
