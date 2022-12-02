package com.kodlamaio.rentalservice.business.concretes;

import com.kodlamaio.rentalservice.business.abstracts.RentalService;
import com.kodlamaio.rentalservice.business.client.CarClient;
import com.kodlamaio.rentalservice.business.createRequest.CreateRentalRequest;
import com.kodlamaio.rentalservice.business.createResponse.CreateRentalResponse;
import com.kodlamaio.rentalservice.business.updateRequest.UpdateRentalRequest;
import com.kodlamaio.rentalservice.business.updateResponse.UpdateRentalResponse;
import com.kodlamaio.rentalservice.dataAccess.RentalRepository;
import com.kodlamaio.rentalservice.entities.Rental;
import com.kodlamaio.rentalservice.kafka.RentalCreateProducer;
import com.kodlamaio.rentalservice.kafka.RentalUpdateProducer;
import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.common.events.RentalUpdatedEvent;
import com.kodlamaio.common.util.mapping.ModelMapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class RentalManager implements RentalService {

    private RentalRepository rentalRepository;
    private RentalUpdateProducer rentalUpdateProducer;
    private RentalCreateProducer rentalCreateProducer;
    private CarClient carClient;
    private ModelMapperService modelMapperService;


    @Override
    public CreateRentalResponse add(CreateRentalRequest createRentalRequest) {
        carClient.checkIfCarAvailable(createRentalRequest.getCarId());
        Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
        rental.setId(UUID.randomUUID().toString());
        rental.setDateStarted(LocalDateTime.now());
        double totalPrice = (createRentalRequest.getDailyPrice() * createRentalRequest.getRentedForDays());
        rental.setTotalPrice(totalPrice);

        Rental rentalCreated = rentalRepository.save(rental);

        RentalCreatedEvent rentalCreatedEvent = new RentalCreatedEvent();
        rentalCreatedEvent.setCarId(rentalCreated.getCarId());
        rentalCreatedEvent.setMessage("Rental Created");

        this.rentalCreateProducer.sendMessage(rentalCreatedEvent);


        CreateRentalResponse createRentalResponse = this.modelMapperService.forResponse().map(rental, CreateRentalResponse.class);

        return createRentalResponse;
    }

    @Override
    public UpdateRentalResponse update(UpdateRentalRequest updateRentalRequest) {
        carClient.checkIfCarAvailable(updateRentalRequest.getOldCarId());
        Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
        rental.setTotalPrice(updateRentalRequest.getDailyPrice() * updateRentalRequest.getRentedForDays());

        rentalRepository.save(rental);

        RentalUpdatedEvent rentalUpdatedEvent = new RentalUpdatedEvent();
        rentalUpdatedEvent.setOldCarId(rentalUpdatedEvent.getOldCarId());
        rentalUpdatedEvent.setNewCarId(rentalUpdatedEvent.getNewCarId());
        rentalUpdatedEvent.setMessage("Rental Updated");

        this.rentalUpdateProducer.sendMessage(rentalUpdatedEvent);

        UpdateRentalResponse updateRentalResponse = this.modelMapperService.forResponse().map(rental, UpdateRentalResponse.class);

        return updateRentalResponse;
    }
}

