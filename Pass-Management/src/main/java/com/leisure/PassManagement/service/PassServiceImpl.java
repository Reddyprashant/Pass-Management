package com.leisure.PassManagement.service;


import com.leisure.PassManagement.dao.CustomerRepository;
import com.leisure.PassManagement.dao.PassRepository;
import com.leisure.PassManagement.dao.VendorRepository;
import com.leisure.PassManagement.exception.ResourceNotFoundException;
import com.leisure.PassManagement.model.Customer;
import com.leisure.PassManagement.model.Pass;
import com.leisure.PassManagement.model.Vendor;
import com.leisure.PassManagement.utility.CommonConstants;
import com.leisure.PassManagement.utility.PassValidityTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@Service
public class PassServiceImpl implements IPassService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PassRepository passRepository;


    /**
     * Add a new pass to the system
     *
     * @param vendorId   ID of the vendor
     * @param customerId ID of the customer purchasing the pass
     * @param passCity   Location of the attraction
     * @return The details of the created pass
     */
    @Override
    public Pass addPassService(String customerId, String vendorId, String passCity) {

        // Get the details of the vendor and customer we are linking

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.CUSTOMER_NOT_FOUND));
        Vendor vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.VENDOR_NOT_FOUND));


        Pass pass1 = new Pass();
        pass1.setCustomer(customer);
        pass1.setVendor(vendor);
        pass1.setPassCity(passCity);
        pass1.setPassValidity(PassValidityTime.passValidFor());
        return passRepository.save(pass1);


    }

    /**
     * Renew a valid pass that is present in the system already
     *
     * @param passId ID of the pass to be renewed
     * @return The details of the renewed pass, or ResourceNotFoundException if no pass was found with the given details
     */
    @Override
    public Pass renewPassService(String passId) {
        System.out.println(passId);
        Pass pass = passRepository.findById(passId)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.PASS_NOT_FOUND));


        if (isValidPass(pass)) {
            pass.setPassValidity(PassValidityTime.renewPassForAnExtraDay());

            passRepository.save(pass);
        }
        return pass;
    }

    /**
     * Validate whether a pass is within its expiry
     *
     * @param pass the Pass to validate
     * @return True  if the pass is valid, or false
     * if no pass is found for the provided details
     */
    @Override
    public boolean isValidPass(Pass pass) {
        if (pass.getPassValidity().isAfter(LocalDateTime.now())) {
            return true;
        }
        return false;
    }

    /**
     * Validate whether a pass is within is valid or not
     *
     * @param passId   Id of the Pass to validate
     * @param vendorId Vendor who is providing the attraction
     * @return Validation object specifying if the pass is valid, the vendorId and passId, or ResourceNotFoundException
     * if no pass is found for the provided details
     */
    @Override
    public boolean isValidPass(String passId, String vendorId) {

        Pass pass = passRepository.findById(passId)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.PASS_NOT_FOUND));

        if (pass.getPassValidity().isAfter(LocalDateTime.now()) && pass.getVendor().getVendorId().equals(vendorId)) {
            return true;
        }
        return false;
    }

    /**
     * Validate the paramters for removePass such that no parameter is null, empty or negative, else throw ResourceNotFoundException
     *
     * @param passId Id of the Pass to be cancelled
     */
    @Override
    public void removePassService(String passId) {

        Pass pass = passRepository.findById(passId)
                .orElseThrow(() -> new ResourceNotFoundException(CommonConstants.PASS_NOT_FOUND));


        passRepository.delete(pass);

    }
}
