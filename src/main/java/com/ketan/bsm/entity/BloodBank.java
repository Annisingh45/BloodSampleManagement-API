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
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankId;
    private String bankName;
    private int emergencyUnitUnitCount;


    @OneToOne(mappedBy = "bloodBank")
    private Address address;

    @OneToMany
    private List<Sample> samples;

    @OneToOne
    private Admin admin;

    @OneToMany
    private List<DonationRequest> donationRequests;

}
