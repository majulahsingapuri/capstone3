package com.example.capstone3;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;
    private String name;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Enumerated(EnumType.STRING)
    private Status status;


       public Account() {
    }

    public Account(Long accountId, String name, AccountType accountType, Status status, Customer customer) {
        this.accountId = accountId;
        this.name = name;
        this.accountType = accountType;
        this.status = status;
        this.customer = customer;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public enum Status {
        Active, Frozen, Deleted;
    }

    public enum AccountType {
        Current, Savings;
    }

    public Long getAccountId() {
        return this.accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    }