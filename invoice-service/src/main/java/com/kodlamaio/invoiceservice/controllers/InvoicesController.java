package com.kodlamaio.invoiceservice.controllers;

import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/invoices")
public class InvoicesController {
    private InvoiceService invoiceService;

    @PostMapping
    public CreateInvoiceResponse add(@Valid @RequestBody CreateInvoiceRequest createInvoiceRequest) {
        return invoiceService.add(createInvoiceRequest);
    }

}
