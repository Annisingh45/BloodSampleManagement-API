package com.ketan.bsm.entity;

import com.ketan.bsm.enums.BloodGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sampleId;
    private BloodGroup bloodGroup;
    private int quantity;
    private boolean availability;
    private int emergencyUnit;
    private int availableUnits;

    @ManyToOne
    private BloodBank bloodBank;





}
