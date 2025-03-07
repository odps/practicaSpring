package es.odec.pruebas.services;

import es.odec.pruebas.models.Invoice;
import es.odec.pruebas.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    InvoiceRepo invoiceRepo;

    @Override
    public ResponseEntity<List<Invoice>> getInvoices() {
        return null;
    }

    @Override
    public ResponseEntity<Invoice> getInvoice(int id) {
        return null;
    }

    @Override
    public ResponseEntity<Invoice> createInvoice() {
        return null;
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public ResponseEntity<Invoice> deleteInvoice(int id) {
        return null;
    }
}
