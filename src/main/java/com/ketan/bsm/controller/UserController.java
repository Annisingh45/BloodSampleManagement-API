package com.ketan.bsm.controller;

import com.ketan.bsm.entity.User;
import com.ketan.bsm.request.UserRequest;
import com.ketan.bsm.response.AdminResponse;
import com.ketan.bsm.response.UserResponse;
import com.ketan.bsm.service.UserService;
import com.ketan.bsm.utility.ResponseStructure;
import com.ketan.bsm.utility.RestResponseBuilder;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@AllArgsConstructor
public class UserController {

    private final UserService service;
    private final RestResponseBuilder responseBuilder;

    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<UserResponse>> registerUser(@RequestBody UserRequest userRequest){
        UserResponse a=service.registerUser(userRequest);
        return responseBuilder.success(HttpStatus.CREATED,"User Created",a);
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<List<UserResponse>>> findAllUsers(){
        List<UserResponse> l= service.findAllUsers();
        return responseBuilder.success(HttpStatus.FOUND,"User Found",l);
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<UserResponse>> updateUserById(@RequestBody @Valid UserRequest userRequest) throws Exception {
        UserResponse u=service.updateUserById(userRequest);
        return responseBuilder.success(HttpStatus.FOUND,"User Updated",u);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<UserResponse>> deleteUserById() throws Exception {
        UserResponse d=service.deleteUserById();
        return responseBuilder.success(HttpStatus.FOUND,"User Deleted",d);
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<UserResponse>> findUserById() throws Exception {
        UserResponse f=service.findUserById();
        return responseBuilder.success(HttpStatus.FOUND,"User Found",f);
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<UserResponse>> promoteUserById(@RequestParam int userId){
        UserResponse f=service.promoteUserById(userId);
        return responseBuilder.success(HttpStatus.FOUND,"User Found",f);
    }
    @PostMapping("/admins")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<ResponseStructure<AdminResponse>> registerAdmin(@RequestBody @Valid UserRequest userRequest){
        AdminResponse a=service.registerAdmin(userRequest);
        return responseBuilder.success(HttpStatus.CREATED,"User Created",a);
    }

    @PreAuthorize("hasAuthority('OWNER_ADMIN') || hasAuthority('GUEST_ADMIN') ")
    @PatchMapping("/users/{userId}")
    public ResponseEntity<ResponseStructure<UserResponse>> verifyUser(@PathVariable int userId,@RequestParam boolean isVerified){
        UserResponse f=service.verifyUserById(userId,isVerified);
        return responseBuilder.success(HttpStatus.FOUND,"User Updated",f);
    }




}
