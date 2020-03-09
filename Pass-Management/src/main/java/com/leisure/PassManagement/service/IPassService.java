package com.leisure.PassManagement.service;

import com.leisure.PassManagement.model.Pass;
import org.springframework.web.bind.annotation.RequestParam;

public interface IPassService {


    Pass addPassService(String customerId, String vendorId, String passCity);

    Pass renewPassService(String passId);

    boolean isValidPass(Pass passId);

    boolean isValidPass(String passId, String vendorId);

     void removePassService(String passId);
}
