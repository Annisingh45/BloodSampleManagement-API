package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.Address;
import com.ketan.bsm.entity.Admin;

import com.ketan.bsm.entity.Hospital;
import com.ketan.bsm.entity.User;
import com.ketan.bsm.enums.AdminType;

import com.ketan.bsm.enums.Role;
import com.ketan.bsm.exception.HospitalNotFoundByIdException;
import com.ketan.bsm.exception.UserNotFoundByIdException;
import com.ketan.bsm.repository.AddressRepository;
import com.ketan.bsm.repository.AdminRepository;
import com.ketan.bsm.repository.HospitalRepository;
import com.ketan.bsm.repository.UserRepository;
import com.ketan.bsm.request.HospitalRequest;

import com.ketan.bsm.response.HospitalResponse;
import com.ketan.bsm.service.HospitalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HospitalServiceImplementation implements HospitalService {


    private final HospitalRepository repository;
    private final AdminRepository adminRepository;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    private HospitalResponse mapToHospitalRes(Hospital hospital) {
        return HospitalResponse.builder()
                .hospitalId(hospital.getHospitalId())
                .hospitalName(hospital.getHospitalName())


                .build();
    }

    private  Hospital mapToHospitalReq(HospitalRequest hospitalRequest, Hospital hospital,Address address) {

        hospital.setHospitalName(hospitalRequest.getHospitalName());


        addressRepository.save(address);
        return hospital;
    }


    @Override
    public HospitalResponse registerHospital(HospitalRequest HospitalRequest,int adminId) {
        Optional<Admin> optional=adminRepository.findById(adminId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("failed to find Admin");
        }

        Hospital hospital=new Hospital();

        hospital= this.mapToHospitalReq(HospitalRequest,hospital,new Address());


        hospital=repository.save(hospital);

        return this.mapToHospitalRes(hospital);
    }



    @Override
    public HospitalResponse findHospitalById(int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Failed to find Hospital");
        }
        Hospital Hospital=optional.get();
        return mapToHospitalRes(Hospital);
    }

    @Override
    public List<HospitalResponse> findAllHospitals()
    {
        List<Hospital> l=repository.findAll();
        if (l.isEmpty()){
            throw new HospitalNotFoundByIdException("failed to find blood bank");
        }
        List<HospitalResponse> res=new ArrayList<>();
        for(Hospital hospital:l)
        {
            res.add(this.mapToHospitalRes(hospital));
        }
        return res;
    }



    @Override
    public HospitalResponse deleteHospitalById(int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if(optional.isPresent()) {
            Hospital a=optional.get();
            repository.delete(a);
            return this.mapToHospitalRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public HospitalResponse updateHospitalById(HospitalRequest HospitalRequest,int HospitalId) {
        Optional<Hospital> optional=repository.findById(HospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("Failed to update");
        }
        Hospital hospital=optional.get();


        return this.mapToHospitalRes(hospital);
    }




}
