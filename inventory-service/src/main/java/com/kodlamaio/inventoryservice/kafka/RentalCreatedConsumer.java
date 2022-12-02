package com.kodlamaio.inventoryservice.kafka;

import com.kodlamaio.common.events.RentalCreatedEvent;
import com.kodlamaio.inventoryservice.business.abstracts.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RentalCreatedConsumer {

    private CarService carService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreatedConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(RentalCreatedEvent event){

        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        this.carService.updateCarState(event.getCarId(),3);
    }

}