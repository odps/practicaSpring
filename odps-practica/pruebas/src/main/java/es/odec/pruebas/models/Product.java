package es.odec.pruebas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_products")
public class Product {
    @Id
    @Column(name = "product_pk")
    private int productId;
    @Column(name = "product_name", length = 25, nullable = false)
    private String productName;
    @Column(name = "product_description", length = 50)
    private String productDescription;
    @Column(name = "product_price", nullable = false)
    private float productPrice;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "stockProduct")
    private List<Stock> stock;

    @OneToMany(mappedBy = "product")
    private List<Order> order;

    protected Product() {}


    @Override
    public String toString() {
        return "Product \nproductId=" + productId + "\nproductName=" + productName + "\nproductDescription=" + productDescription + "\nproductPrice=" + productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
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

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }
}
