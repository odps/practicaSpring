package es.odec.pruebas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_orders")
public class Order {
    @Id
    @Column(name = "order_pk")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_shop")
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "order_product")
    private Product product;

    @Column(name = "order_quantity")
    private int quantity;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;

    protected Order() {}

    public int getId() {
        return id;
    }

    public List<Stock> getProduct() {
        return product;
    }

    public void setProduct(List<Stock> product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }


    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
