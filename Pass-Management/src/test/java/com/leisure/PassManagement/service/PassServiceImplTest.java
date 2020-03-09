package com.leisure.PassManagement.service;

import com.leisure.PassManagement.dao.CustomerRepository;
import com.leisure.PassManagement.dao.PassRepository;
import com.leisure.PassManagement.dao.VendorRepository;
import com.leisure.PassManagement.model.Customer;
import com.leisure.PassManagement.model.Pass;
import com.leisure.PassManagement.model.Vendor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class PassServiceImplTest {


    @TestConfiguration
    static class PassServiceImplTestConfiguration {

        @Bean
        public PassServiceImpl iPassService() {
            return new PassServiceImpl();
        }
    }

    @Autowired
    private IPassService iPassService;


    @MockBean
    private PassRepository passRepository;
    @MockBean
    private CustomerRepository customerRepository;
    @MockBean
    private VendorRepository vendorRepository;

    @Test
    void testAddPassService() {
        String uuid = UUID.randomUUID().toString();

        //Given
        Customer customer = new Customer();
        customer.setCustomerId("john@gmail.com");
        customer.setCustomerCity("Boston");
        customer.setCustomerName("John");

        Vendor vendor = new Vendor();
        vendor.setVendorName("Amazon");
        when(customerRepository.findById("john@gmail.com")).thenReturn(Optional.of(customer));
        when(vendorRepository.findById(uuid)).thenReturn(Optional.of(vendor));

        //When addPassService is Invoked
        iPassService.addPassService("john@gmail.com", uuid, "Boston");

        //Then verify that the passRepository.save() was called 1 times
        Mockito.verify(passRepository, Mockito.times(1))
                .save(anyObject());
    }

    @Test
    void testRenewPassServiceInvalid() {
        String uuid = UUID.randomUUID().toString();
        Pass pass = new Pass();
        pass.setPassValidity(LocalDateTime.now());
        when(passRepository.findById(uuid)).thenReturn(Optional.of(pass));

        iPassService.renewPassService(uuid);

        Mockito.verify(passRepository, Mockito.times(1))
                .findById(uuid);
        Mockito.verify(passRepository, Mockito.times(0))
                .save(pass);
    }

    @Test
    void testRenewPassServiceValid() {
        String uuid = UUID.randomUUID().toString();
        Pass pass = new Pass();

        //Given
        pass.setPassValidity(LocalDateTime.now().with(LocalTime.MAX));
        when(passRepository.findById(uuid)).thenReturn(Optional.of(pass));

        //When renewPass service is invoked
        iPassService.renewPassService(uuid);

        //Then verify that the find by Id
        Mockito.verify(passRepository, Mockito.times(1))
                .findById(uuid);
        Mockito.verify(passRepository, Mockito.times(1))
                .save(pass);
    }

    @Test
    void testIsValidPassVendor() {
        String passId = UUID.randomUUID().toString();
        Pass pass = new Pass();
        Vendor vendor = new Vendor();
        pass.setVendor(vendor);
        pass.setPassId(passId);
        pass.setPassValidity(LocalDateTime.now().with(LocalTime.MAX));
        when(passRepository.findById(passId)).thenReturn(Optional.of(pass));

        boolean result = iPassService.isValidPass(passId, vendor.getVendorId());

        assertTrue(result);

    }

    @Test
    void testIsValidPassVendorFail() {
        String passId = UUID.randomUUID().toString();
        Pass pass = new Pass();
        Vendor vendor = new Vendor();
        pass.setVendor(vendor);
        pass.setPassId(passId);
        pass.setPassValidity(LocalDateTime.now());
        when(passRepository.findById(passId)).thenReturn(Optional.of(pass));

        boolean result = iPassService.isValidPass(passId, vendor.getVendorId());

        assertFalse(result);
    }

}