package com.leisure.PassManagement.controller;

import com.leisure.PassManagement.model.Pass;
import com.leisure.PassManagement.service.IPassService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "pass")
@Api(description = "Pass management related endpoints")
public class PassController {

    @Autowired
    private IPassService iPassService;


    /**
     * Add a new pass to the system
     * @param customerId Customer purchasing the pass
     * @param vendorId   Vendor of the pass attraction
     * @param passCity   City the attraction is in
     * @return The details of the created pass
     */
    @PostMapping(value = "/add",consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Add pass details",
            notes = "Return pass details or throws 400")
    public ResponseEntity addPass(@RequestParam String customerId, @RequestParam String vendorId, @RequestParam String passCity) {
        try {
            Pass pass = iPassService.addPassService(customerId, vendorId, passCity);
            return new ResponseEntity<Object>(pass, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Renew an existing for an extra day and  expired pass cannot be renewed
     *
     * @param passId ID of the pass to renew
     * @return The details of the renewed pass
     */
    @PatchMapping("/renew/{passId}")
    @ApiOperation(value = "Renew pass for an extra day",
            notes = "Return pass details or throws 400")
    public ResponseEntity renewPass(@PathVariable String passId) {
        try {
            return new ResponseEntity(iPassService.renewPassService(passId), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


    /**
     * Cancel an existing pass
     *
     * @param passId ID of the pass to renew
     * @return The details of the renewed pass
     */
    @DeleteMapping("/cancel/{passId}")
    @ApiOperation(value = "Cancel the pass",
            notes = "return succesfull removal of pass or throws 400")
    public ResponseEntity removePass(@PathVariable String passId) {
        iPassService.removePassService(passId);
        try {
            return new ResponseEntity("Pass with ID : " + passId + " has been removed", HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }


}
