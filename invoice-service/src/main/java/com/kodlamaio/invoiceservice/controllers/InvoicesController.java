package com.kodlamaio.invoiceservice.controllers;

import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class InvoicesController {
    private InvoiceService invoiceService;

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return invoiceService.add(createInvoiceRequest);
    }

}