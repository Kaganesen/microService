package com.kodlamaio.rentalservice.kafka;

import com.torukobyte.common.events.RentalCreatedEvent;
import com.torukobyte.common.events.RentalUpdatedEvent;
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
public class RentalCreateProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentalCreateProducer.class);

    private NewTopic createTopic;

    private KafkaTemplate<String, RentalCreatedEvent> kafkaCreatedTemplate;

    public void sendMessage(RentalCreatedEvent rentalCreatedEvent) {
        LOGGER.info(String.format("Rental created event => %s", rentalCreatedEvent.toString()));

        Message<RentalCreatedEvent> message = MessageBuilder
                .withPayload(rentalCreatedEvent)
                .setHeader(KafkaHeaders.TOPIC, createTopic.name()).build();

        kafkaCreatedTemplate.send(message);
    }


}
