package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.Admin;
import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.entity.Hospital;
import com.ketan.bsm.entity.Sample;
import com.ketan.bsm.enums.AdminType;
import com.ketan.bsm.exception.BloodBankNotFoundByIdException;
import com.ketan.bsm.exception.SampleNotFoundByIdException;
import com.ketan.bsm.exception.UserNotFoundByIdException;
import com.ketan.bsm.repository.BloodBankRepository;
import com.ketan.bsm.repository.SampleRepository;
import com.ketan.bsm.request.SampleRequest;
import com.ketan.bsm.response.SampleResponse;
import com.ketan.bsm.service.SampleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SampleServiceImplementation implements SampleService {



    private final SampleRepository repository;
    private final BloodBankRepository bloodBankRepository;

    private SampleResponse mapToSampleRes(Sample sample) {
        return SampleResponse.builder()
                .sampleId(sample.getSampleId())
                .bloodGroup(sample.getBloodGroup())
                .quantity(sample.getQuantity())


                .build();
    }

    private  Sample mapToSampleReq(SampleRequest SampleRequest, Sample sample) {
        sample.setBloodGroup(SampleRequest.getBloodGroup());
        sample.setQuantity(SampleRequest.getQuantity());
        if(SampleRequest.getQuantity()<sample.getEmergencyUnit()){
            sample.setEmergencyUnit(SampleRequest.getQuantity());
            return sample;
        } else if (SampleRequest.getQuantity()==sample.getEmergencyUnit()) {
            return sample;
        }else {
            sample.setAvailableUnits(SampleRequest.getQuantity()-sample.getEmergencyUnit());
            return sample;
        }

    }


    @Override
    public SampleResponse registerSample(SampleRequest SampleRequest,int bloodBankId) {
        Optional<BloodBank> optional=bloodBankRepository.findById(bloodBankId);
        if (optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("failed to find Admin");
        }
        BloodBank BloodBank=optional.get();
        Sample sample=new Sample();
        sample.setEmergencyUnit(BloodBank.getEmergencyUnitUnitCount());
        sample=this.mapToSampleReq(SampleRequest,sample);

        sample= this.mapToSampleReq(SampleRequest,new Sample());
        BloodBank=bloodBankRepository.save(BloodBank);
        sample.setBloodBank(BloodBank);
        sample=repository.save(sample);
        return this.mapToSampleRes(sample);
    }

    @Override
    public SampleResponse findSampleById(int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if (optional.isEmpty()){
            throw new SampleNotFoundByIdException("Failed to find Sample");
        }
        Sample Sample=optional.get();
        return mapToSampleRes(Sample);
    }

    @Override
    public List<SampleResponse> findAllSamples()
    {
        List<Sample> l=repository.findAll();
        if (l.isEmpty()){
            throw new SampleNotFoundByIdException("failed to find blood bank");
        }
        List<SampleResponse> res=new ArrayList<>();
        for(Sample Sample:l)
        {
            res.add(this.mapToSampleRes(Sample));
        }
        return res;
    }



    @Override
    public SampleResponse deleteSampleById(int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if(optional.isPresent()) {
            Sample a=optional.get();
            repository.delete(a);
            return this.mapToSampleRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public SampleResponse updateSampleById(SampleRequest SampleRequest,int SampleId) {
        Optional<Sample> optional=repository.findById(SampleId);
        if (optional.isEmpty()){
            throw new SampleNotFoundByIdException("Failed to update");
        }
        Sample Sample=this.mapToSampleReq(SampleRequest,optional.get());
        return this.mapToSampleRes(Sample);
    }









}
