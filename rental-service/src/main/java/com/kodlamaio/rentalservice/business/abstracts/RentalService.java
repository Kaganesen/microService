package com.kodlamaio.rentalservice.business.abstracts;

import com.kodlamaio.rentalservice.business.createRequest.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.createResponse.CreateRentalResponse;

public interface RentalService {

    CreateRentalResponse add (CreateRentalRequest createRentalRequest);


}
