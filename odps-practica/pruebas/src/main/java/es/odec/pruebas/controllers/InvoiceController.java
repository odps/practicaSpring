package es.odec.pruebas.controllers;

import es.odec.pruebas.models.Invoice;
import es.odec.pruebas.services.InvoiceService;
import net.kaczmarzyk.spring.data.jpa.domain.EqualIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LessThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping("/pagedList")
    public ResponseEntity<?> getPagedInvoices(
            @PageableDefault(page = 0, size = 10, sort = "invoicePk", direction = Sort.Direction.ASC) Pageable pageable,
            @Conjunction({
                    @Or({@Spec(path = "invoiceTotal", params = "totalGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "invoiceTotal", params = "totalLess", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "createdAt", params = "createdAfter", spec = GreaterThanOrEqual.class),
                            @Spec(path = "createdAt", params = "createdBefore", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "user.firstName", params = "hasName", spec = EqualIgnoreCase.class),
                            @Spec(path = "user.firstName", params = "likeName", spec = LikeIgnoreCase.class)}),
                    @Or({@Spec(path = "order.product.productName", params = "hasProduct", spec = EqualIgnoreCase.class),
                            @Spec(path = "order.product.productName", params = "likeProduct", spec = LikeIgnoreCase.class)})
            }) Specification<Invoice> spec) {
        return invoiceService.getInvoicesPaged(pageable, spec);
    }

    @GetMapping("/invoiceCount")
    public ResponseEntity<?> invoiceCount(
            @Conjunction({
                    @Or({@Spec(path = "invoiceTotal", params = "totalGreater", spec = GreaterThanOrEqual.class),
                            @Spec(path = "invoiceTotal", params = "totalLess", spec = LessThanOrEqual.class)}),
                    @Or({@Spec(path = "createdAt", params = "createdAfter", spec = GreaterThanOrEqual.class),
                            @Spec(path = "createdAt", params = "createdBefore", spec = LessThanOrEqual.class)})
            }) Specification<Invoice> spec) {
        return invoiceService.invoiceCount(spec);
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
