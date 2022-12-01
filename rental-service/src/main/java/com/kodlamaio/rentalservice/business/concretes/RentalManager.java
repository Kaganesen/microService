package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.createRequest.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.createResponse.CreateRentalResponse;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.kafka.RentalProducer;
import com.kodlamaio.rentalservice.repository.RentalRepository;
import com.torukobyte.common.events.RentalCreatedEvent;
import com.torukobyte.common.util.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private RentalProducer rentalProducer;
    private ModelMapperService modelMapperService;


    @Override
    public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setId(UUID.randomUUID().toString());
        Rental rentalCreated = rentalRepository.save(rental);

        RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
        rentalCreatedEvent.setCarId(rentalCreated.getCarId());
        rentalCreatedEvent.setMessage("Rental Created");

        this.rentalProducer.sendMessage(rentalCreatedEvent);

        CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);

        return createRentalResponse;
    }
}

