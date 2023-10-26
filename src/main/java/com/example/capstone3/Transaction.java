package com.example.capstone3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "Transactions")
public class Transaction {

  @Id
  @Column(name = "Transaction_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long TransactionId;

  private LocalDate datecreated;
  private TransactionType type;
  private double amount;

  @ManyToOne
  @JoinColumn(name = "account_id")
  private Account account;

  public Transaction() {}

  public Transaction(
      Long TransactionId,
      LocalDate datecreated,
      TransactionType type,
      double amount,
      Account account) {
    super();
    this.TransactionId = TransactionId;
    this.datecreated = datecreated;
    this.type = type;
    this.amount = amount;
    this.account = account;
  }

  public enum TransactionType {
    Credit,
    Debit;
  }

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

  public TransactionType getType() {
    return this.type;
  }

  public void setType(TransactionType type) {
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
