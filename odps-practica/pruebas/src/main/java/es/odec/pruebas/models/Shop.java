package es.odec.pruebas.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "op_shops")
public class Shop {
    @Id
    @Column(name = "shop_pk")
    private int shopId;
    @Column(name = "shop_name")
    private String shopName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_owner")
    private User shopOwner;

    @Column(name = "shop_adress", unique = true)
    private String shopAdress;
    @Column(name = "shop_email")
    private String shopEmail;
    @Column(name = "shop_phone")
    private String shopPhone;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "stockOwner")
    private List<Stock> stocks;

    @OneToMany(mappedBy = "shop")
    private List<Invoice> invoices;

    @OneToMany(mappedBy = "shop")
    private List<Order> orders;

    protected Shop() {}

    public int getShopId() {
        return shopId;
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

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

}
