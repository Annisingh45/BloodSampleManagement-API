package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.*;
import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.enums.Role;
import com.ketan.bsm.exception.BloodBankNotFoundByIdException;
import com.ketan.bsm.exception.UserNotFoundByIdException;
import com.ketan.bsm.mappper.AddressMapper;
import com.ketan.bsm.mappper.SampleMapper;
import com.ketan.bsm.repository.AdminRepository;
import com.ketan.bsm.repository.BloodBankRepository;
import com.ketan.bsm.repository.SampleRepository;
import com.ketan.bsm.repository.UserRepository;
import com.ketan.bsm.response.BloodBankPageResponse;
import com.ketan.bsm.request.BloodBankRequest;
import com.ketan.bsm.response.BloodBankResponse;
import com.ketan.bsm.response.SampleResponse;
import com.ketan.bsm.service.BloodBankService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;




@Service
@AllArgsConstructor
public class BloodBankServiceImplementation implements BloodBankService {


    private final BloodBankRepository repository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final AddressMapper addressMapper;
    private final SampleMapper sampleMapper;
    private final SampleRepository sampleRepository;

    private BloodBankResponse mapToBloodBankRes(BloodBank bloodBank) {
        return BloodBankResponse.builder()
                .bankId(bloodBank.getBankId())
                .bankName(bloodBank.getBankName())
                .build();
    }

    private  BloodBank mapToBloodBankReq(BloodBankRequest bloodBankRequest, BloodBank bloodBank, Address address) {
        bloodBank.setBankName(bloodBankRequest.getBankName());
        bloodBank.setEmergencyUnitUnitCount(bloodBankRequest.getEmergencyUnitCount());


        return bloodBank;
    }


    @Override
    public BloodBankResponse registerBloodBank(BloodBankRequest BloodBankRequest,int adminId) {
        Optional<Admin> optional=adminRepository.findById(adminId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("failed to find Admin");
        }
        Admin admin=optional.get();
        User user = admin.getUser();
        user.setRole(Role.OWNER_ADMIN);
        List<Admin> l=new ArrayList<>();
        l.add(admin);
        BloodBank bloodBank=new BloodBank();


        bloodBank= this.mapToBloodBankReq(BloodBankRequest,bloodBank,new Address());


        bloodBank=repository.save(bloodBank);
        userRepository.save(user);
        adminRepository.save(admin);
        return this.mapToBloodBankRes(bloodBank);
    }

    @Override
    public BloodBankResponse findBloodBankById(int BloodBankId) {
        Optional<BloodBank> optional=repository.findById(BloodBankId);
        if (optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("Failed to find BloodBank");
        }
        BloodBank BloodBank=optional.get();
        return mapToBloodBankRes(BloodBank);
    }

    @Override
    public Page<BloodBank> findAllBloodBanks(List<String> locations, BloodGroup bloodGroup,int page,int size)
    {
        List<BloodGroup> l = listBloodGroup(bloodGroup);


        Pageable pageable= PageRequest.of(page,size, Sort.Direction.ASC);
         Page<BloodBank> bloodBanks=  repository.findByAddress_CityInAndSamples_BloodGroupIn(locations,l,pageable);
         if (bloodBanks.isEmpty()){
             throw new BloodBankNotFoundByIdException("NOT FOUND");
         }

        return bloodBanks;
    }

    private static List<BloodGroup> listBloodGroup(BloodGroup bloodGroup) {
        List<BloodGroup> l=new ArrayList<>();
        switch (bloodGroup){
            case  A_POSITIVE:l.add(BloodGroup.A_POSITIVE);
            l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.O_POSITIVE);l.add(BloodGroup.A_NEGETIVE);break;
            case A_NEGETIVE:
                l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.A_NEGETIVE);break;
            case B_POSITIVE:
                l.add(BloodGroup.B_POSITIVE);
                l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.O_POSITIVE);l.add(BloodGroup.B_NEGETIVE);break;
            case B_NEGETIVE:
                l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.B_NEGETIVE);break;
            case O_NEGETIVE:
                l.add(BloodGroup.O_NEGETIVE);break;
            case O_POSITIVE:
                l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.O_POSITIVE);break;
            case AB_NEGATIVE:l.add(BloodGroup.A_POSITIVE);l.add(BloodGroup.AB_NEGATIVE);l.add(BloodGroup.AB_POSITIVE);
                l.add(BloodGroup.O_NEGETIVE);l.add(BloodGroup.O_POSITIVE);l.add(BloodGroup.A_NEGETIVE);break;
            default:
                break;
        }
        return l;
    }

    @Override
    public List<BloodBankPageResponse> generateBloodBankPageResponse(Page<BloodBank> bloodBanks,BloodGroup bloodGroup) {

        List<BloodBank> bloddbanks= bloodBanks.toList();
//        Collections.sort(bloddbanks);
        List<BloodBankResponse> bloodBankResponses=new ArrayList<>();
        List<BloodBankPageResponse> bloodBankPageResponses=new ArrayList<>();

        for (BloodBank bloodBank:bloddbanks) {
            List<BloodGroup> bloodGroups=listBloodGroup(bloodGroup);
            List<Sample> samples=sampleRepository.findByBloodBankAndBloodGroupIn(bloodBank,bloodGroups);
            List<SampleResponse> sampleResponses=new ArrayList<>();
            for (Sample sample:samples){
                sampleResponses.add(sampleMapper.mapToSampleRes(sample));
            }

            bloodBankPageResponses.add(this.mapToBloodBankPageMapper(bloodBank, sampleResponses));
        }
        return bloodBankPageResponses;
    }

    private BloodBankPageResponse mapToBloodBankPageMapper(BloodBank bloodBank, List<SampleResponse> sampleResponses) {
        return BloodBankPageResponse.builder()
                .bloodBankId(bloodBank.getBankId())
                .bloodName(bloodBank.getBankName())
                .address(addressMapper.mapToAddressRes(bloodBank.getAddress()))
                .samples(sampleResponses)
                .build();
    }

    private  BloodBankResponse mapToResponse(BloodBank bloodBank) {
       return BloodBankResponse.builder().bankId(bloodBank.getBankId())
                .bankName(bloodBank.getBankName()).build();
    }


    @Override
    public BloodBankResponse deleteBloodBankById(int BloodBankId) {
        Optional<BloodBank> optional=repository.findById(BloodBankId);
        if(optional.isPresent()) {
            BloodBank a=optional.get();
            repository.delete(a);
            return this.mapToBloodBankRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public BloodBankResponse updateBloodBankById(BloodBankRequest BloodBankRequest,int BloodBankId) {
        Optional<BloodBank> optional=repository.findById(BloodBankId);
        if (optional.isEmpty()){
            throw new BloodBankNotFoundByIdException("Failed to update");
        }
        BloodBank bloodBank=optional.get();
        Address address=bloodBank.getAddress();
        bloodBank=this.mapToBloodBankReq(BloodBankRequest,bloodBank,address);
        return this.mapToBloodBankRes(bloodBank);
    }




}
