package com.kodlamaio.invoiceservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient (value = "InvoiceClientService", url = "http://localhost:9010/payment/api/payments")
public interface InvoiceClientService {
}
