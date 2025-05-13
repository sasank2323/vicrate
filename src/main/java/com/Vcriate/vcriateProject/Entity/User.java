package com.Vcriate.vcriateProject.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="user1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double balance = 0.0;

    public User() {}

    public void setId(Long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
        this.balance = 0.0;
    }

    // Getters and setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public Double getBalance() { return balance; }
    public void setName(String name) { this.name = name; }
    public void setBalance(Double balance) { this.balance = balance; }
}
