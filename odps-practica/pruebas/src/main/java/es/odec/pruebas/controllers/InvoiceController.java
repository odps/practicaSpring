package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Invoice;
import es.odec.pruebas.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    // Recoger datos de invoice
    @GetMapping("/list")
    public ResponseEntity<List<Invoice>> getInvoices() {
        return invoiceService.getInvoices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable int id) {
        return invoiceService.getInvoice(id);
    }

    // Crear un nuevo invoice
    @PostMapping("/create")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return invoiceService.createInvoice(invoice);
    }

    // Actualizar un invoice
    @PutMapping("/edit/{id}")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice, @PathVariable int id) {
        return invoiceService.updateInvoice(invoice, id);
    }

    // Borrar un invoice
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Invoice> deleteInvoice(@PathVariable int id) {
        return invoiceService.deleteInvoice(id);
    }
}
