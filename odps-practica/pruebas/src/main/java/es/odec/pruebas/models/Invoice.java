package es.odec.pruebas.models;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;
import java.time.LocalDate;

@Entity
@Table(name = "op_invoices")
public class Invoice {
    @Id
    @Column(name = "invoice_pk")
    private int invoicePk;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "shop_id")
    private Shop shopId;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "user_id")
    private User userId;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "order_id")
    private Order orderId;

    @Column(name = "invoice_total")
    private int invoiceTotal;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    protected Invoice(){}

    public int getInvoicePk() {
        return invoicePk;
    }

    public Shop getShopId() {
        return shopId;
    }

    public User getUserId() {
        return userId;
    }

    public Order getOrderId() {
        return orderId;
    }

    public void setInvoiceTotal(int invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public int getInvoiceTotal() {
        return invoiceTotal;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
