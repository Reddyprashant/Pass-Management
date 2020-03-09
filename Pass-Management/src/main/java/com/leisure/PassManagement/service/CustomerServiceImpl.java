package com.leisure.PassManagement.service;

import com.leisure.PassManagement.dao.CustomerRepository;
import com.leisure.PassManagement.exception.BadRequestException;
import com.leisure.PassManagement.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * Validate the paramters for saveCustomer such that no parameter is null, empty or negative, else throw BadRequestException
     *
     * @param customers add Customer to the system
     */
    @Override
    public Customer saveCustomer(Customer customers) {

          Customer customer = customerRepository.findById(customers.getCustomerId())
                   .orElseThrow(()-> new BadRequestException("customer with this ID already exists in the system"));

        if (!customer.getCustomerId().isEmpty() && !customer.getCustomerCity().isEmpty() && !customer.getCustomerName().isEmpty()) {

            return customerRepository.save(customer);

        } else {
            throw new BadRequestException("Please enter all the details of the customer");
        }


    }

}
