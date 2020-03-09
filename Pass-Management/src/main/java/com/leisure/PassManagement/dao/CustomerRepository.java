package com.leisure.PassManagement.dao;

import com.leisure.PassManagement.model.Customer;
import org.springframework.data.repository.CrudRepository;


public interface CustomerRepository extends CrudRepository<Customer, String> {
}
