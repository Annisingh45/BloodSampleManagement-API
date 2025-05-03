package com.ketan.bsm.entity;

import com.ketan.bsm.enums.BloodGroup;
import com.ketan.bsm.enums.TransactionType;
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
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private LocalDate date;
    private LocalTime time;
    private int noOfUnits;
    private BloodGroup bloodGroup;
    private TransactionType transactionType;

    @ManyToOne
    private User user;

    @ManyToOne
    private Hospital hospital;

    @ManyToOne
    private BloodBank bloodBank;
}
