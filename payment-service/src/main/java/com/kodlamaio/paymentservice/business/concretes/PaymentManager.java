package com.kodlamaio.paymentservice.business.concretes;

import com.kodlamaio.common.events.PaymentCreatedEvent;
import com.kodlamaio.common.util.exceptions.BusinessException;
import com.kodlamaio.common.util.mapping.ModelMapperService;
import com.kodlamaio.paymentservice.business.abstracts.PaymentService;
import com.kodlamaio.paymentservice.business.requests.CreatePaymentRequest;
import com.kodlamaio.paymentservice.business.responses.CreatePaymentResponse;
import com.kodlamaio.paymentservice.client.PaymentClientService;
import com.kodlamaio.paymentservice.dataAccess.PaymentRepository;
import com.kodlamaio.paymentservice.entities.Payment;
import com.kodlamaio.paymentservice.kafka.PaymentProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PaymentManager implements PaymentService {

    private PaymentRepository paymentRepository;
    private ModelMapperService modelMapperService;
    private PaymentProducer paymentProducer;
    private PaymentClientService paymentClientService;

    @Override
    public CreatePaymentResponse add(CreatePaymentRequest createPaymentRequest) {
        checkBalanceEnough(createPaymentRequest.getBalance(),createPaymentRequest.getRentalId());

        Payment payment = modelMapperService.forRequest().map(createPaymentRequest, Payment.class);
        payment.setId(UUID.randomUUID().toString());

        Payment createdPayment = paymentRepository.save(payment);

        PaymentCreatedEvent paymentCreatedEvent = new PaymentCreatedEvent();
        paymentCreatedEvent.setRentalId(createdPayment.getRentalId());
        paymentCreatedEvent.setMessage("Payment Created");

        this.paymentProducer.sendMessage(paymentCreatedEvent);

        CreatePaymentResponse createPaymentResponse = modelMapperService.forResponse().map(payment, CreatePaymentResponse.class);

        return createPaymentResponse;
    }

    @Override
    public void updateStatus(String id, int status) {
        Payment payment = this.paymentRepository.findById(id).get();
        payment.setStatus(status);
        paymentRepository.save(payment);

    }

    @Override
    public void delete(String id) {
        paymentRepository.deleteById(id);

    }

    private void checkBalanceEnough(double balance, String rentalId) {
        if (balance<paymentClientService.getTotalPrice(rentalId)) {
            throw new BusinessException("BALANCE.IS.NOT.ENOUGH");
        }
    }



}
