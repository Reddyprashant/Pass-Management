package com.leisure.PassManagement.service;


import com.leisure.PassManagement.dao.VendorRepository;
import com.leisure.PassManagement.model.Vendor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class VendorServiceImplTest {


    @TestConfiguration
    static class VendorServiceImplTestConfiguration {

        @Bean
        public IVendorService iVendorService() {
            return new VendorServiceImpl();
        }
    }

    @Autowired
    private IVendorService iVendorService;

    @MockBean
    private VendorRepository vendorRepository;

    @Test
    void saveVendor() {

        Vendor vendor = createVendor();

        Mockito.when(vendorRepository.save(vendor))
                .then(invocationOnMock -> {
                    vendor.setVendorId("abcd123");
                    return vendor;
                });
        // when saveAccountdetails is invoked
        Vendor newVendor = iVendorService.saveVendor(vendor);

        // Then verify that the id is not null
        assertNotNull(newVendor.getVendorId(), "new vendor id should exist");

        // Then verify that the id is same as the mock id
        assertEquals("abcd123", newVendor.getVendorId(), "vendor id should be same");

        // Then verify that the repository.save() was called 1 times
        Mockito.verify(vendorRepository, Mockito.times(1))
                .save(vendor);
    }

    private Vendor createVendor() {
        Vendor vendor = new Vendor();
        vendor.setVendorId(null);
        vendor.setVendorName("Aquarium");
        return vendor;
    }
}