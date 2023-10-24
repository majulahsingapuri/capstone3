package com.example.capstone3;
import jakarta.persistence.*;

@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstname;
    private String lastname;


    public Customer() {
    }

    public Customer(Long customerId, String firstname, String lastname) {
        super();
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    
}

