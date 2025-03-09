package es.odec.pruebas.services;

import es.odec.pruebas.models.Invoice;
import es.odec.pruebas.models.Role;
import es.odec.pruebas.repositories.InvoiceRepo;
import es.odec.pruebas.repositories.RoleRepo;
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
        return ResponseEntity.ok().body(invoiceRepo.findAll());
    }

    @Override
    public ResponseEntity<Invoice> getInvoice(int id) {
        if (invoiceRepo.existsById(id)) {
            return ResponseEntity.ok().body(invoiceRepo.findById(id).get());
        } else return ResponseEntity.status(404).build();
    }

    @Override
    public ResponseEntity<Invoice> createInvoice(Invoice invoice) {
        return ResponseEntity.ok().body(invoiceRepo.save(invoice));
    }

    @Override
    public ResponseEntity<Invoice> updateInvoice(Invoice invoiceNew, int id) {
        if (!invoiceRepo.existsById(id)) {
            return ResponseEntity.status(404).build();
        }
        Invoice invoice = invoiceRepo.findById(id).get();
        invoice.setUser(invoiceNew.getUser());
        invoice.setOrder(invoiceNew.getOrder());
        invoice.setInvoiceTotal(invoiceNew.getInvoiceTotal());
        Invoice saved = invoiceRepo.save(invoice);
        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<Invoice> deleteInvoice(int id) {
        if (invoiceRepo.existsById(id)) {
            Invoice invoice = invoiceRepo.findById(id).get();
            invoiceRepo.deleteById(id);
            return ResponseEntity.ok().body(invoice);
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
