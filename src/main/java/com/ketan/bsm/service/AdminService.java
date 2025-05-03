package com.ketan.bsm.service;

import com.ketan.bsm.request.UserRequest;
import com.ketan.bsm.response.UserResponse;

public interface AdminService {
    public UserResponse registerAdmin(UserRequest user);

    public UserResponse deleteAdminById(int adminId);

    public UserResponse updateUserById(UserRequest userRequest,int userId);
}
