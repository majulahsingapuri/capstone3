package com.example.capstone3;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Transactions")
public class Transaction {

    @Id
    @Column(name = "Transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long TransactionId;
    private LocalDate datecreated;
    private String type;
    private double amount;


    public Transaction() {
        super();
    }

    public Transaction(Long id, String username, String password, Account account) {
        super();
        this.TransactionId = TransactionId;
        this.type = type;
        this.amount = amount;
        this.account = account;

    }

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;


    public Long getTransactionId() {
        return this.TransactionId;
    }

    public void setTransactionId(Long TransactionId) {
        this.TransactionId = TransactionId;
    }

    public LocalDate getDatecreated() {
        return this.datecreated;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }




}



    
