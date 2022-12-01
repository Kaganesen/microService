package com.kodlamaio.rentalservice.business.createResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalResponse {

    private String id;

    private String carId;

    private int rentedForDays;

    private double dailyPrice;



}
