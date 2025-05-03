package com.ketan.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hospitalId;
    private String hospitalName;

    @OneToMany
    private List<Admin> admin;

    @OneToMany(mappedBy = "hospital")
    private List<Transaction> transaction;

    @OneToOne(mappedBy = "hospital")
    private Address address;

    @OneToMany
    private List<DonationRequest> donationRequests;


}
