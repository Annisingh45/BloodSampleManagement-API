package com.ketan.bsm.service;

import com.ketan.bsm.request.SampleRequest;
import com.ketan.bsm.response.SampleResponse;

import java.util.List;

public interface SampleService {
    public SampleResponse registerSample(SampleRequest Sample,int bloodBankId);

    public List<SampleResponse> findAllSamples();

    public SampleResponse findSampleById(int SampleId);

    public SampleResponse deleteSampleById(int SampleId);

    public SampleResponse updateSampleById(SampleRequest SampleRequest,int SampleId);


}
