package com.ketan.bsm.mail;

import com.ketan.bsm.entity.*;
import com.ketan.bsm.exception.HospitalNotFoundByIdException;
import com.ketan.bsm.repository.BloodBankRepository;
import com.ketan.bsm.repository.DonationRequestRepository;
import com.ketan.bsm.repository.HospitalRepository;
import com.ketan.bsm.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class NotificationWorker {
    private final UserRepository userRepository;
    private final DonationRequestRepository donationRequestRepository;
    private final HospitalRepository hospitalRepository;
    private final BloodBankRepository bloodBankRepository;
    private final MailService service;

    @Scheduled(fixedRate = 60000)
    public void sendBloodDonationRequestNotification() throws MessagingException {
        List<DonationRequest> donationRequests=donationRequestRepository.findByRequestCompleted(false);

        for (DonationRequest donationRequest:donationRequests){
            Organization org=this.findOrganizationDetails(donationRequest);
            sendBloodDonationRequestNotification(org,donationRequest);
        }

    }
    public void sendBloodDonationRequestNotification(Organization org,DonationRequest donationRequest) throws MessagingException {
        List<User> users=userRepository.findByAvailableCityInAndBloodGroupIn(donationRequest.getCites(),donationRequest.getBloodGroups());

        for (User user:users){
            Map<String, Object> map=new HashMap<>();
            map.put("OrganizationName",org.getOrgName());
            map.put("OrganizationAddress",org.getOrgAddress());
            map.put("bloodGroup",user.getBloodGroup().name());
            map.put("surveyLink","");
            map.put("donationLink","");

            String text= service.generateContent("DonationRequest",map);

            service.sendMail(user.getEmail(),"Urgent:Blood required",text);
        }
    }

    private Organization findOrganizationDetails(DonationRequest donationRequest) {
        String organizationName=null;
        String organizationAddress=null;
        switch (donationRequest.getType()){
            case HOSPITAL ->{
                Optional<Hospital> optional=hospitalRepository.findByDonationRequests(donationRequest);
                if (optional.isEmpty()){
                    throw new HospitalNotFoundByIdException("111111");
                }
                Hospital hospital=optional.get();
                organizationName=hospital.getHospitalName();
                Address address=hospital.getAddress();
                organizationAddress= getOrganizationAddress(address);

            }
            case BLOODBANK ->{
                Optional<BloodBank> optional=bloodBankRepository.findByDonationRequests(donationRequest);
                if (optional.isEmpty()){
                    throw new HospitalNotFoundByIdException("222222");
                }
                BloodBank bloodBank=optional.get();
                organizationName=bloodBank.getBankName();
                Address address=bloodBank.getAddress();

                organizationAddress= getOrganizationAddress(address);

            }
        }
        Organization organization=new Organization();
        organization.orgName=organizationName;
        organization.orgAddress=organizationAddress;
        return organization;
    }

    private static String getOrganizationAddress(Address address) {
        return address.getAddressLine() +" \n  "+ address.getArea() +
                " \n  "+ address.getCity() +" \n  "+ address.getState()
                +" \n  "+ address.getCountry() +" \n  "+ address.getPinCode();
    }

    @Getter
    @Setter
    public static class Organization{
        public String orgName;
        public String orgAddress;

    }
}
