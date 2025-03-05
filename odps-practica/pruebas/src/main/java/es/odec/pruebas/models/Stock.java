package es.odec.pruebas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_stock")
public class Stock {
    @Id
    @Column(name = "stock_pk")
    private int stockPk;

    @ManyToOne
    @JoinColumn(name = "stock_owner")
    private Shop stockOwner;

    @ManyToOne
    @JoinColumn(name = "stock_product")
    private Product stockProduct;

    @Column(name = "stock_quantity")
    private int stockQuantity;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @ManyToOne()
    private Order orders;

    public Stock() {}

    public int getStockPk() {
        return stockPk;
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

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
