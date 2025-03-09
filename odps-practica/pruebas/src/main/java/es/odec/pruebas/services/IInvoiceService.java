package es.odec.pruebas.services;

import es.odec.pruebas.models.Invoice;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IInvoiceService {
    ResponseEntity<List<Invoice>> getInvoices();

    ResponseEntity<Invoice> getInvoice(int id);

    ResponseEntity<Invoice> createInvoice(Invoice invoice);

    ResponseEntity<Invoice> updateInvoice(Invoice invoice, int id);

    ResponseEntity<Invoice> deleteInvoice(int id);

}
