package com.kodlamaio.rentalservice.kafka;

import com.kodlamaio.common.events.RentalUpdatedEvent;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RentalUpdateProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateProducer.class);

    private NewTopic updateTopic;

    private KafkaTemplate<String, RentalUpdatedEvent> kafkaUpdatedTemplate;

    public void sendMessage (RentalUpdatedEvent rentalUpdatedEvent){
        LOGGER.info(String.format("Rental updated event => %s", rentalUpdatedEvent.toString()));

        Message<RentalUpdatedEvent> message = MessageBuilder
                .withPayload(rentalUpdatedEvent)
                .setHeader(KafkaHeaders.TOPIC, updateTopic.name()).build();

        kafkaUpdatedTemplate.send(message);
    }


}
