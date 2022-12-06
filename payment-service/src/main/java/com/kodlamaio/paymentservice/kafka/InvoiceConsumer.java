package com.kodlamaio.paymentservice.kafka;

import com.kodlamaio.common.events.InvoiceCreatedEvent;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceConsumer.class);
    private PaymentService paymentService;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "create")
    public void consume(InvoiceCreatedEvent event) {
        LOGGER.info(String.format("Order event received in stock service => %s", event.toString()));
        paymentService.updateStatus(event.getPaymentId(), 2);
        // save the order event into the database
    }


}
