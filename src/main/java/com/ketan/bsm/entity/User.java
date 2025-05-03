package com.ketan.bsm.entity;

import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.enums.Gender;
import com.ketan.bsm.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private Gender gender;
    private String password;
    private String phoneNumber;
    private BloodGroup bloodGroup;
    private LocalDate lastDonated;
    private int age;
    private String availableCity;
    private boolean verified;
    @CreatedDate
    private LocalDate createdAt;
    @LastModifiedDate
    private LocalDate lastModifiedAt;
    private Role role;


    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER)
    private Admin admin;

    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;


    @OneToOne(mappedBy = "user")
    private Address address;


}
