package es.odec.pruebas.models;

import jakarta.persistence.*;

import javax.xml.crypto.Data;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "op_users")
public class User {
    @Id
    @Column(name = "user_pk")
    private int userId;
    @Column(name = "user_firstname")
    private String firstName;
    @Column(name = "user_lastname")
    private String lastName;
    @Column(name = "user_email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "user_password")
    private String password;

    @ManyToOne
    @Column(name = "user_role")
    private Role role;

    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "modified_at")
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "shopOwner")
    private List<Shop> shops;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

    public User() {}

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
