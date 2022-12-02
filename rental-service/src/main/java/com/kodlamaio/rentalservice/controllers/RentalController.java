package com.kodlamaio.rentalservice.controllers;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.createRequest.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.createResponse.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.updateRequest.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.updateResponse.UpdateRentalResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/rentals")
public class RentalController {
    private final RentalService rentalService;

    @PostMapping()
    CreateRentalResponse add (@RequestBody @Valid CreateRentalRequest createRentalRequest){
        return rentalService.add(createRentalRequest);
    }
    @PutMapping()
    UpdateRentalResponse update (@RequestBody @Valid UpdateRentalRequest updateRentalRequest){
        return rentalService.update(updateRentalRequest);
    }
}
