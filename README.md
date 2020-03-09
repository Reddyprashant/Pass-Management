# Pass-Management
Pass Management System

## Database
* A running MySQL instance with the database `pass_management` created



## Run the application
Maven build to build an application or run application by IDE

##EndPoints
### /customer/add

    RequestBody:
     {
		  	"customerId": "abcd@gmail.com",
		  	"customerName":"abcd",
		  	"customerCity": "boston"
    }
    returns the details of created customer 
    
### /vendor/add

    RequestBody:
    {
		  "vendorName": "Amazon"
    }
    returns the details of created vendor
  
### /pass/addPass

    parameters:
      customerId: String
      vendorId:  String UUID
      PassCity:  String
    
    return the details of created passs which valid for that one day (until 23.59)
    
    
### /pass/renew/{passId)
 
     parameter:
       passid:  String UUID
       
       If the pass is not not expired then the pass will be renewed for extra one day and if pass is expired we cannot renew the pass.
       
       
### /pass/cancel/{passId}
    
       parameter:
        passid:  String UUID
       
       If pass with input passId is present in the system then it will be cancelled else throw not found error
       
 
### /vendor/{vendorId}/pass/{passId}
      parameters:
       customerId: String
       vendorId:  String UUID
    
     return whether pass is valid or not by checking with the expiry date of pass and checking whether Vednor with the input vendorId has issues the pass with passId
    

##API Documentation using Swagger:

  1. http://localhost:8080/swagger-ui.html to check the API documentation.

  2. we can verify the methods implemented in our web service. 
    
