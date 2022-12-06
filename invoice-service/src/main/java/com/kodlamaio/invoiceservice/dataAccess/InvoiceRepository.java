package com.kodlamaio.invoiceservice.dataAccess;

import com.kodlamaio.invoiceservice.kafka.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {

}
