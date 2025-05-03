package com.ketan.bsm.entity;

import com.ketan.bsm.enums.AdminType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int adminId;


    @OneToOne
    private User user;

    @ManyToOne
    private Hospital hospital;

    @OneToOne
    private BloodBank bloodBank;


}
