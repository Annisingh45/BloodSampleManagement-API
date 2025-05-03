package com.ketan.bsm.entity;

import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.enums.Donated;
import com.ketan.bsm.enums.OrganizationType;
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
@AllArgsConstructor
@NoArgsConstructor
public class DonationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    private List<String> cites;
    private List<BloodGroup> bloodGroups;
    private OrganizationType type;
    private boolean requestCompleted;


}
