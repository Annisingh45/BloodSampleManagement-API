package com.ketan.bsm.service;

import com.ketan.bsm.entity.Hospital;
import com.ketan.bsm.request.HospitalRequest;
import com.ketan.bsm.response.HospitalResponse;

import java.util.List;

public interface HospitalService {
    public HospitalResponse registerHospital(HospitalRequest Hospital,int adminId);

    public List<HospitalResponse> findAllHospitals();

    public HospitalResponse findHospitalById(int HospitalId);

    public HospitalResponse deleteHospitalById(int HospitalId);

    public HospitalResponse updateHospitalById(HospitalRequest HospitalRequest,int HospitalId);


}
