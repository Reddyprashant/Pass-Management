package com.leisure.PassManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table
public class Vendor {

    @Id
    @Column(name = "vendor_id", unique = true)
    private String vendorId;

    @Column(name = "vendor_name", unique = true)
    private String vendorName;


    public Vendor() {
        this.vendorId = UUID.randomUUID().toString();
    }



    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }


}
