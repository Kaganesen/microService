package com.kodlamaio.paymentservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "rentalId")
    private String rentalId;

    @Column(name = "cardNo")
    private String cardNo;

    @Column(name = "cardHolder")
    private String cardHolder;

    @Column(name = "cvv")
    private int cvv;

    @Column(name = "cardDate")
    private LocalDate cardDate;

    @Column(name = "balance")
    private double balance;

    @Column(name = "status")
    private int status;
}
