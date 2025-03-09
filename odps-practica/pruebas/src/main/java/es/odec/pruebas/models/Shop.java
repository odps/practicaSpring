package es.odec.pruebas.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_shops")
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_pk")
    private int shopId;
    @Column(name = "shop_name", length = 50)
    private String shopName;

    @ManyToOne
    @JoinColumn(name = "shop_owner", nullable = false)
    @JsonIgnoreProperties({"shops", "username", "password", "invoices"})
    private User shopOwner;

    @Column(name = "shop_adress", length = 100)
    private String shopAdress;
    @Column(name = "shop_email", length = 50)
    private String shopEmail;
    @Column(name = "shop_phone", length = 15)
    private String shopPhone;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDate createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "stockOwner")
    private List<Stock> stocks;

    @JsonIgnoreProperties("shop")
    @OneToMany(mappedBy = "shop")
    private List<Order> orders;

    protected Shop() {
    }

    public Shop(String shopName, User shopOwner, String shopAdress, String shopEmail, String shopPhone) {
        this.shopName = shopName;
        this.shopOwner = shopOwner;
        this.shopAdress = shopAdress;
        this.shopEmail = shopEmail;
        this.shopPhone = shopPhone;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public User getShopOwner() {
        return shopOwner;
    }

    public void setShopOwner(User shopOwner) {
        this.shopOwner = shopOwner;
    }

    public String getShopAdress() {
        return shopAdress;
    }

    public void setShopAdress(String shopAdress) {
        this.shopAdress = shopAdress;
    }

    public String getShopEmail() {
        return shopEmail;
    }

    public void setShopEmail(String shopEmail) {
        this.shopEmail = shopEmail;
    }

    public String getShopPhone() {
        return shopPhone;
    }

    public void setShopPhone(String shopPhone) {
        this.shopPhone = shopPhone;
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

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
