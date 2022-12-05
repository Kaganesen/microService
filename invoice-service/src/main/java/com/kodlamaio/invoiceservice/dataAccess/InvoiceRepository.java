package com.kodlamaio.invoiceservice.dataAccess;

import com.kodlamaio.invoiceservice.entities.concretes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice,String> {

}
