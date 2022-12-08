package com.kodlamaio.paymentservice.api.controllers.models;


import com.kodlamaio.paymentservice.business.concretes.CreditCardManager;
import com.kodlamaio.paymentservice.business.requests.create.CreateCreditCardRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MakePayment {


    CreateCreditCardRequest createCreditCardRequest;

    String rentalId;

    CreditCardManager.CardSaveInformation cardSaveInformation;


}
