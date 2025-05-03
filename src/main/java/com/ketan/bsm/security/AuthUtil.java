package com.ketan.bsm.security;

import com.ketan.bsm.entity.Admin;
import com.ketan.bsm.entity.User;
import com.ketan.bsm.exception.UserNotFoundByIdException;
import com.ketan.bsm.repository.AdminRepository;
import com.ketan.bsm.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@AllArgsConstructor
@Component
public class AuthUtil {

    private  final UserRepository userRepository;
    private final AdminRepository adminRepository;

    public String getCurrentUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getCurrentUser() throws Exception{

        return userRepository.findByEmail(this.getCurrentUserName())
                 .orElseThrow(()-> new UsernameNotFoundException("Failed to authenticate"));

    }
    public Admin getCurrentAdmin() throws Exception{
        return adminRepository.findByUser_Email(this.getCurrentUserName())
                .orElseThrow(()-> new UsernameNotFoundException("Failed to authenticate"));
    }
}
