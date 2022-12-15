package com.kodlamaio.paymentservice.business.outServices;

public class AkbankPosService {

    public boolean makePayment(String cardOwner, String cardNumber, String cardCvv, String cardExpirationDate, double totalPrice) {

        return cardOwner.equals("KAGAN ESEN") || cardOwner.equals("ENGIN DEMIROG");
    }

}