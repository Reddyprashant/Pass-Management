package com.leisure.PassManagement.model;


import com.leisure.PassManagement.utility.LocalDateTimeConverter;

import javax.persistence.*;

import java.time.LocalDateTime;

import java.util.UUID;

@Entity
@Table
public class Pass {

    @Id
    @Column(name = "pass_id", unique = true)
    private String passId;

    @Column
    private String passCity;

    @Column
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime passValidity;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;


    public Pass() {
        this.passId = UUID.randomUUID().toString();
    }

    public String getPassId() {

        return passId;
    }

    public void setPassId(String passId) {

        this.passId = passId;
    }

    public String getPassCity() {
        return passCity;
    }

    public void setPassCity(String passCity) {
        this.passCity = passCity;
    }

    public LocalDateTime getPassValidity() {

        return passValidity;
    }

    public void setPassValidity(LocalDateTime passValidity) {
        this.passValidity = passValidity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }


}
