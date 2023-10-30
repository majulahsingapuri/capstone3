package com.example.capstone3;

import jakarta.persistence.*;
import java.util.List;

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

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Transaction> transactions;

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  public Account() {}

  public Account(
      Long accountId,
      String name,
      AccountType accountType,
      Status status,
      List<Transaction> transactions,
      Customer customer) {
    this.accountId = accountId;
    this.name = name;
    this.accountType = accountType;
    this.status = status;
    this.transactions = transactions;
    this.customer = customer;
  }

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public enum Status {
    Active,
    Frozen,
    Deleted;
  }

  public enum AccountType {
    Current,
    Savings;
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
