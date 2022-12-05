package com.kodlamaio.invoiceservice.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoices")

public class Invoice {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "paymentId")
    private String paymentId;

    @Column(name = "customerFirstName")
    private String customerFirstName;

    @Column(name = "customerLastName")
    private String customerLastName;

    @Column(name = "tax")
    private double tax;

    @Column(name = "totalPrice")
    private double totalPrice;

    @Column(name = "address")
    private String address;
}
