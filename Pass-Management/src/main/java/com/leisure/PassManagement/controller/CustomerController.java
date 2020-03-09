package com.leisure.PassManagement.controller;


import com.leisure.PassManagement.model.Customer;
import com.leisure.PassManagement.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "customer")
@Api(description = "Customer management related endpoints")
public class CustomerController {

    @Autowired
    private ICustomerService iCustomerService;


    /**
     * Add a new pass to the system
     *
     * @param customer details of the Customer purchasing pass
     * @return The details of the created customer
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create and Save the customer details",
            notes = "Return customer details or throws 400")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            return new ResponseEntity(iCustomerService.saveCustomer(customer), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }


}
