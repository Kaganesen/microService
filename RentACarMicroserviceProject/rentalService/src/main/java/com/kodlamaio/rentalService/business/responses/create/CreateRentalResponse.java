package com.kodlamaio.rentalService.business.responses.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalResponse {

    private String id;
    private String carId;
    private LocalDate dateStarted;
    private int rentedForDays;
    private double dailyPrice;
    private double totalPrice;
}
