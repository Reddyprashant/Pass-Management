package com.leisure.PassManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Customer {

    @Id
    @Column(name = "customer_id", unique = true)
    private String customerId;

    @Column
    private String customerName;

    @Column
    private String customerCity;




    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String city) {
        this.customerCity = city;
    }
}
