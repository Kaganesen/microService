package com.kodlamaio.invoiceservice.business.abstracts;

import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.responses.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.UpdateInvoiceResponse;

import java.util.List;

public interface InvoiceService {

    List<GetAllInvoicesResponse> getAll();
    CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest);
    void delete(String id);
    UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest);
    GetInvoiceResponse getById(String id);
}
