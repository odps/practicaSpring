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
    @Column(name = "product_name")
    private String productName;
    @Column(name = "product_description")
    private String productDescription;
    @Column(name = "product_price")
    private double productPrice;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "stockProduct")
    private List<Stock> stock;

    protected Product() {}


    @Override
    public String toString() {
        return "Product \nproductId=" + productId + "\nproductName=" + productName + "\nproductDescription=" + productDescription + "\nproductPrice=" + productPrice;
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

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }


    public LocalDate getCreatedAt() {
        return createdAt;
    }


    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
