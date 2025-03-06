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
    @JoinColumn(name = "order_shop", nullable = false)
    private Shop shop;

    @ManyToOne
    @JoinColumn(name = "order_product", nullable = false)
    private Product product;

    @Column(name = "order_quantity", nullable = false)
    private int quantity;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "order")
    private List<Invoice> invoices;

    protected Order() {}


}
