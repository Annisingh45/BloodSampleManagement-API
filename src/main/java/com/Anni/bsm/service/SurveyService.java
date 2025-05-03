package com.ketan.bsm.service;

import com.ketan.bsm.request.SurveyRequest;
import com.ketan.bsm.response.SurveyResponse;

import java.util.List;

public interface SurveyService {
    public SurveyResponse registerSurvey(SurveyRequest Survey) throws Exception;

    public List<SurveyResponse> findAllSurveys();

    public SurveyResponse findSurveyById(int SurveyId);

    public SurveyResponse deleteSurveyById(int SurveyId);

    public SurveyResponse updateSurveyById(SurveyRequest SurveyRequest,int SurveyId);


}
