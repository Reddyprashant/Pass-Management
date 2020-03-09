package com.leisure.PassManagement.service;

import com.leisure.PassManagement.dao.CustomerRepository;
import com.leisure.PassManagement.model.Customer;
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
class CustomerServiceImplTest {

    @TestConfiguration
    static class CustomerServiceImplTestConfiguration {

        @Bean
        public ICustomerService iCustomerService() {
            return new CustomerServiceImpl();
        }
    }

    @Autowired
    private ICustomerService iCustomerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    void testSaveCustomer() {

        Customer customer = new Customer();
        customer.setCustomerId("pra@gmail.com");
        customer.setCustomerName("pra");
        customer.setCustomerCity("boston");

        Mockito.when(customerRepository.save(customer))
                .then(invocationOnMock -> {
                    customer.setCustomerId("pra@gmail.com");
                    return customer;
                });
        // when saveAccountdetails is invoked
        Customer newCustomer = iCustomerService.saveCustomer(customer);

        // Then verify that the id is not null
        assertNotNull(newCustomer.getCustomerId(), "new customer id should exist");

        // Then verify that the id is same as the mock id
        assertEquals("pra@gmail.com", newCustomer.getCustomerId(), "customer id should be same");

        // Then verify that the repository.save() was called 1 times
        Mockito.verify(customerRepository, Mockito.times(1))
                .save(customer);


    }

}