package com.kodlamaio.rentalservice.business.updateRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRentalRequest {


    private String oldCarId;

    private String newCarId;

    private int rentedForDays;

    private double dailyPrice;




}
