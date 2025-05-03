package com.ketan.bsm.repository;

import com.ketan.bsm.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository  extends JpaRepository<Survey,Integer> {
}
