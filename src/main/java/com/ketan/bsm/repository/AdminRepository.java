package com.ketan.bsm.repository;

import com.ketan.bsm.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
    public Optional<Admin> findByUser_Email(String userName);
}
