package com.ketan.bsm.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Donation {
    @Id
    @GeneratedValue
    private int donationId;
    private LocalDate date;
    private LocalTime time;

    @ManyToOne
    private User user;

    @OneToOne
    private DonationLead donationLead;

}
