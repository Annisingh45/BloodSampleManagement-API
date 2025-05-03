package com.ketan.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DonationLead {
    @Id
    @GeneratedValue
    private int leadId;
    private LocalDate date;
    private LocalTime time;

    @OneToMany
    private List<DonationRequest> donationRequest;

    @OneToOne
    private Donation donation;

}
