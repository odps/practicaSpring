package es.odec.pruebas.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_pk")
    private int id;

    @ManyToOne
    @JoinColumn(name = "order_shop", nullable = false)
    private Shop shop;

    @JsonIgnoreProperties({"order", "invoices"})
    @ManyToOne
    @JoinColumn(name = "order_product", nullable = false)
    private Product product;

    @Column(name = "order_quantity", nullable = false)
    private int quantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "order")
    @JsonIgnoreProperties("order")
    private List<Invoice> invoices;

    protected Order() {
    }

    public Order(Shop shop, Product product, int quantity) {
        this.shop = shop;
        this.product = product;
        this.quantity = quantity;
    }

    public Shop getShop() {
        return shop;
    }

    public int getId() {
        return id;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }
}
