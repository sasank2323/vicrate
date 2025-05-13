package com.Vcriate.vcriateProject.Entity;
import jakarta.persistence.*;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String type; // "CREDIT" or "DEBIT"
    private Double amount;


    public Transaction(Long userId, String type, Double amount) {
        this.userId = userId;
        this.type = type;
        this.amount = amount;
    }

    public Transaction()
    {

    }
    // Getters and Setters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getType() { return type; }
    public Double getAmount() { return amount; }

    public void setId(Long id) { this.id = id; }
    public void setUserId(Long userId) { this.userId = userId; }
    public void setType(String type) { this.type = type; }
    public void setAmount(Double amount) { this.amount = amount; }
}

