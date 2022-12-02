package com.kodlamaio.rentalservice.business.abstracts;

import com.kodlamaio.rentalservice.business.createRequest.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.createResponse.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.updateRequest.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.updateResponse.UpdateRentalResponse;

public interface RentalService {

    CreateRentalResponse add (CreateRentalRequest createRentalRequest);
    UpdateRentalResponse update (UpdateRentalRequest updateRentalRequest);



}
