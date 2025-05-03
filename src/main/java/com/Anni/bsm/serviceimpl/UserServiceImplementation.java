package com.ketan.bsm.serviceimpl;

import com.ketan.bsm.entity.Address;
import com.ketan.bsm.entity.Admin;
import com.ketan.bsm.entity.Hospital;
import com.ketan.bsm.enums.Role;
import com.ketan.bsm.exception.HospitalNotFoundByIdException;
import com.ketan.bsm.exception.UserNotFoundByIdException;
import com.ketan.bsm.repository.AddressRepository;
import com.ketan.bsm.repository.AdminRepository;
import com.ketan.bsm.repository.UserRepository;
import com.ketan.bsm.request.UserRequest;
import com.ketan.bsm.response.AdminResponse;
import com.ketan.bsm.response.HospitalResponse;
import com.ketan.bsm.response.UserResponse;
import com.ketan.bsm.security.AuthUtil;
import com.ketan.bsm.service.UserService;
import lombok.AllArgsConstructor;
import com.ketan.bsm.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository repository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final AuthUtil authUtil;


    private  UserResponse mapToUserRes(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .userName(user.getUserName())
                .gender(user.getGender())
                .lastDonated(user.getLastDonated())
                .age(user.getAge())
                .availableCity(user.getAvailableCity())
                .verified(user.isVerified())
                .createdAt(user.getCreatedAt())
                .bloodGroup(user.getBloodGroup())
                .lastModifiedAt(user.getLastModifiedAt())
                .role(user.getRole())



                .build();
    }

    private  User mapToUserReq(UserRequest userRequest, User user) {
        user.setUserName(userRequest.getUserName());
        user.setEmail(userRequest.getEmail());
        user.setGender(userRequest.getGender());
        user.setPassword(userRequest.getPassword());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setBloodGroup(userRequest.getBloodGroup());
        user.setAge(userRequest.getAge());
        user.setAvailableCity(userRequest.getAvailableCity());
        user.setLastDonated(userRequest.getLastDonated());

        return user;
    }


    @Override
    public UserResponse registerUser(UserRequest userRequest) {
        User user=new User();
        user.setRole(Role.USER);
        user= this.mapToUserReq(userRequest,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=repository.save(user);
        return this.mapToUserRes(user);
    }

    @Override
    public UserResponse findUserById() throws Exception {
        int userId=authUtil.getCurrentUser().getUserId();
        Optional<User> optional=repository.findById(userId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("Failed to find user");
        }
        User user=optional.get();
        return mapToUserRes(user);
    }

    @Override
    public List<UserResponse> findAllUsers()
    {
        List<User> l=repository.findAll();
        if (l.isEmpty()){
            throw new UserNotFoundByIdException("failed to find blood bank");
        }
        List<UserResponse> res=new ArrayList<>();
        for(User user:l)
        {
            res.add(this.mapToUserRes(user));
        }
        return res;
    }



    @Override
    public UserResponse deleteUserById() throws Exception {
        int userId=authUtil.getCurrentUser().getUserId();
        Optional<User> optional=repository.findById(userId);
        if(optional.isPresent()) {
            User a=optional.get();
            repository.delete(a);
            return this.mapToUserRes(a);
        }else {
            throw new RuntimeException("Failed to delete Actor");
        }
    }

    @Override
    public UserResponse updateUserById(UserRequest userRequest) throws Exception {

        User user=authUtil.getCurrentUser();

        user=this.mapToUserReq(userRequest,user);
        return this.mapToUserRes(user);
    }

    @Override
    public UserResponse promoteUser(UserRequest userRequest) {
        return null;
    }

    @Override
    public UserResponse promoteUserById(int userId) {
        Optional<User> optional=repository.findById(userId);
        if (optional.isEmpty()){
            throw new UserNotFoundByIdException("Failed to update");
        }
        User user=optional.get();
        user.setRole(Role.GUEST_ADMIN);
        repository.save(user);
        Admin admin=new Admin();
       admin.setUser(user);
       adminRepository.save(admin);
        return this.mapToUserRes(user);
    }

    @Override
    public AdminResponse registerAdmin(UserRequest userRequest) {
        User user=new User();
        user.setRole(Role.OWNER_ADMIN);
        user= this.mapToUserReq(userRequest,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user=repository.save(user);
        UserResponse userResponse=this.mapToUserRes(user);
        Admin admin=new Admin();
        admin.setUser(user);

        adminRepository.save(admin);
        return AdminResponse.builder()
                .userResponse(userResponse)
                .adminId(admin.getAdminId())
                .build();
    }

    @Override
    public UserResponse verifyUserById(int userId, boolean isVerified) {
        Optional<User> optional=repository.findById(userId);
        if (optional.isEmpty()){
            throw  new UserNotFoundByIdException("Failed to find User");
        }
        User user=optional.get();
        user.setVerified(isVerified);

        return this.mapToUserRes(user);
    }


}
