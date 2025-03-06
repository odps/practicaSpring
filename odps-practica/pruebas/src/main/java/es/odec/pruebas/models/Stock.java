package es.odec.pruebas.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "op_stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_pk")
    private int stockPk;

    @ManyToOne
    @JoinColumn(name = "stock_owner", nullable = false)
    private Shop stockOwner;

    @ManyToOne
    @JoinColumn(name = "stock_product", nullable = false)
    private Product stockProduct;

    @Column(name = "stock_quantity", nullable = false)
    private int stockQuantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @ManyToOne()
    @JoinColumn(name = "stock_orders")
    private Order orders;

    public Stock() {
    }


    public int getStockPk() {
        return stockPk;
    }

    public void setStockPk(int stockPk) {
        this.stockPk = stockPk;
    }

    public Shop getStockOwner() {
        return stockOwner;
    }

    public void setStockOwner(Shop stockOwner) {
        this.stockOwner = stockOwner;
    }

    public Product getStockProduct() {
        return stockProduct;
    }

    public void setStockProduct(Product stockProduct) {
        this.stockProduct = stockProduct;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Order getOrders() {
        return orders;
    }

    public void setOrders(Order orders) {
        this.orders = orders;
    }
}
