package com.ketan.bsm.repository;

import com.ketan.bsm.entity.User;
import com.ketan.bsm.enums.BloodGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    public Optional<User> findByEmail(String username);

    public Optional<User> findByPassword(String password);

    List<User> findByAvailableCityInAndBloodGroupIn(List<String> cites, List<BloodGroup> bloodGroups);

}
