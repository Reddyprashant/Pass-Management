package com.leisure.PassManagement.controller;


import com.leisure.PassManagement.model.Vendor;
import com.leisure.PassManagement.service.IPassService;
import com.leisure.PassManagement.service.IVendorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "vendor")
@Api(description = "Vendor management related endpoints")
public class VendorController {

    @Autowired
    private IVendorService iVendorService;

    @Autowired
    private IPassService iPassService;


    /**
     * Add a new pass to the system
     *
     * @param vendor details of the Vendor of attraction
     * @return The details of the created vendor
     */
    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create and Save vendor details",
            notes = "return Vendor deatils or throws 400")
    public ResponseEntity<Object> createVendor(@RequestBody Vendor vendor) {

        return new ResponseEntity<Object>(iVendorService.saveVendor(vendor), HttpStatus.OK);
    }

    @GetMapping("/{vendorId}/pass/{passId}")
    @ApiOperation(value = "Validate the pass against vendor Id and pass Id",
            notes = "return successful validation or throws 400")
    public ResponseEntity validatePass(@PathVariable String vendorId, @PathVariable String passId) {

        if (iPassService.isValidPass(passId, vendorId)) {
            return new ResponseEntity("pass has been validated", HttpStatus.OK);
        }
        return new ResponseEntity("please enter the correct passId and vendorId to validate the pass", HttpStatus.BAD_REQUEST);

    }
}
