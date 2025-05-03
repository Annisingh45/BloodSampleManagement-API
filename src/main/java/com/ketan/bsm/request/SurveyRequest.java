package com.ketan.bsm.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyRequest {
    private boolean preMedicalCondition;
    private boolean consumedAlcohol;
    private boolean consumedTobacco;
    private boolean addictive;
    private boolean medicines;
}
