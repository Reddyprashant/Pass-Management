package com.leisure.PassManagement.service;

import com.leisure.PassManagement.dao.VendorRepository;
import com.leisure.PassManagement.exception.BadRequestException;
import com.leisure.PassManagement.model.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorServiceImpl implements IVendorService {

    @Autowired
    private VendorRepository vendorRepository;

    /**
     * Validate the paramters for saveVendor such that no parameter is null, empty or negative
     *
     * @param vendor add Vendor to system
     */
    @Override
    public Vendor saveVendor(Vendor vendor) {

        if (vendor.getVendorName() != null) {
            return vendorRepository.save(vendor);
        } else {
            throw new BadRequestException("Please enter the Vendor name");
        }
    }
}
