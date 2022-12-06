package com.kodlamaio.invoiceservice.business.concretes;

import com.kodlamaio.common.events.InvoiceCreatedEvent;
import com.kodlamaio.common.util.mapping.ModelMapperService;
import com.kodlamaio.invoiceservice.business.abstracts.InvoiceService;
import com.kodlamaio.invoiceservice.business.requests.CreateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.requests.UpdateInvoiceRequest;
import com.kodlamaio.invoiceservice.business.responses.create.CreateInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.get.GetAllInvoicesResponse;
import com.kodlamaio.invoiceservice.business.responses.get.GetInvoiceResponse;
import com.kodlamaio.invoiceservice.business.responses.update.UpdateInvoiceResponse;
import com.kodlamaio.invoiceservice.dataAccess.InvoiceRepository;
import com.kodlamaio.invoiceservice.kafka.concretes.Invoice;
import com.kodlamaio.invoiceservice.kafka.InvoiceProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class InvoiceManager implements InvoiceService {


    private InvoiceRepository invoiceRepository;
    private ModelMapperService modelMapperService;
    private InvoiceProducer invoiceProducer;

    @Override
    public List<GetAllInvoicesResponse> getAll() {
        return null;
    }

    @Override
    public CreateInvoiceResponse add(CreateInvoiceRequest createInvoiceRequest) {
        Invoice invoice = modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
        invoice.setId(UUID.randomUUID().toString());

        Invoice createdInvoice = invoiceRepository.save(invoice);

        InvoiceCreatedEvent invoiceCreatedEvent = new InvoiceCreatedEvent();
        invoiceCreatedEvent.setPaymentId(createdInvoice.getPaymentId());
        invoiceCreatedEvent.setMessage("Payment Created");

        this.invoiceProducer.sendMessage(invoiceCreatedEvent);

        CreateInvoiceResponse createPaymentResponse = modelMapperService.forResponse().map(invoice, CreateInvoiceResponse.class);

        return createPaymentResponse;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public UpdateInvoiceResponse update(UpdateInvoiceRequest updateInvoiceRequest) {
        return null;
    }

    @Override
    public GetInvoiceResponse getById(String id) {
        return null;
    }
}
