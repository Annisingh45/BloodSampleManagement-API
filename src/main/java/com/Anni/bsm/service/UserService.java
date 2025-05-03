package com.ketan.bsm.service;

import com.ketan.bsm.entity.User;
import com.ketan.bsm.request.UserRequest;
import com.ketan.bsm.response.AdminResponse;
import com.ketan.bsm.response.UserResponse;
import jakarta.validation.Valid;

import java.util.List;


public interface UserService {
    public UserResponse registerUser(UserRequest user);

    public List<UserResponse> findAllUsers();

    public UserResponse findUserById() throws Exception;

    public UserResponse deleteUserById() throws Exception;

    public UserResponse updateUserById(UserRequest userRequest) throws Exception;

    public UserResponse promoteUser(UserRequest userRequest);

    public UserResponse promoteUserById(int userId);

    AdminResponse registerAdmin(@Valid UserRequest userRequest);

    UserResponse verifyUserById(int userId, boolean isVerified);
}



