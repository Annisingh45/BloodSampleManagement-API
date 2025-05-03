package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.Address;
import com.ketan.bsm.entity.BloodBank;
import com.ketan.bsm.entity.Hospital;
import com.ketan.bsm.entity.User;
import com.ketan.bsm.exception.AddressNotFoundByIdException;
import com.ketan.bsm.exception.BloodBankNotFoundByIdException;
import com.ketan.bsm.exception.HospitalNotFoundByIdException;
import com.ketan.bsm.repository.AddressRepository;
import com.ketan.bsm.repository.BloodBankRepository;
import com.ketan.bsm.repository.HospitalRepository;
import com.ketan.bsm.repository.UserRepository;
import com.ketan.bsm.request.AddressRequest;
import com.ketan.bsm.response.AddressResponse;
import com.ketan.bsm.security.AuthUtil;
import com.ketan.bsm.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AddressServiceImplementation implements AddressService {


    private final AddressRepository repository;
    private final AuthUtil authUtil;
    private final HospitalRepository hospitalRepository;
    private final UserRepository userRepository;
    private final BloodBankRepository bloodBankRepository;

    private AddressResponse mapToAddressRes(Address address) {
        return AddressResponse.builder()
                .addressId(address.getAddressId())
                .addressLine(address.getAddressLine())
                .landMark(address.getLandMark())
                .area(address.getArea())
                .state(address.getState())
                .country(address.getCountry())
                .pinCode(address.getPinCode())
                .city(address.getCity())
                .build();
    }

    private  Address mapToAddressReq(AddressRequest addressRequest, Address address) {
        address.setAddressLine(addressRequest.getAddressLine());
        address.setLandMark(addressRequest.getLandMark());
        address.setArea(addressRequest.getArea());
        address.setCity(addressRequest.getCity());
        address.setState(addressRequest.getState());
        address.setCountry(addressRequest.getCountry());
        address.setPinCode(addressRequest.getPinCode());
        return address;
    }




    @Override
    public AddressResponse registerUserAddress(AddressRequest addressRequest) throws Exception {
        User user=authUtil.getCurrentUser();
        Address address=this.mapToAddressReq(addressRequest,new Address());
        address.setUser(user);
        userRepository.save(user);
        repository.save(address);
        return this.mapToAddressRes(address);
    }

    @Override
    public AddressResponse registerHospitalAddress(AddressRequest addressRequest,int hospitalId) {
        Optional<Hospital> optional=hospitalRepository.findById(hospitalId);
        if (optional.isEmpty()){
            throw new HospitalNotFoundByIdException("failed to find hospital");
        }
        Hospital hospital=optional.get();
        Address address=this.mapToAddressReq(addressRequest,new Address());
        address.setHospital(hospital);
        repository.save(address);
        return this.mapToAddressRes(address);
    }

    @Override
    public AddressResponse registerBllodBankAddress(AddressRequest addressRequest,int bloodBankId) {
        Optional<BloodBank> optional=bloodBankRepository.findById(bloodBankId);
        if (optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("failed to find blood bank");
        }
        BloodBank bloodBank=optional.get();
        Address address=this.mapToAddressReq(addressRequest,new Address());

        address.setBloodBank(bloodBank);
        bloodBankRepository.save(bloodBank);
        repository.save(address);
        return this.mapToAddressRes(address);
    }

}
