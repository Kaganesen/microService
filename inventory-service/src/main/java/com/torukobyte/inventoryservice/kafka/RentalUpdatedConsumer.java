package com.torukobyte.inventoryservice.kafka;

import com.torukobyte.common.events.RentalUpdatedEvent;
import com.torukobyte.inventoryservice.business.abstracts.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RentalUpdatedConsumer {

    private CarService carService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalUpdatedConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume (RentalUpdatedEvent event){
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        this.carService.updateCarState(event.getOldCarId(),1);
        this.carService.updateCarState(event.getNewCarId(), 3);
    }
}
