APIVERSION: 1.0
APIDOMAIN: XXX

# Customer Profile API
Customer profile APIs will be used to perfrom CRUD operations by Web and Mobile UI clients.

## Authentication
Note: Description on how Authentication is done comes here

## Media Types
The Media type for Request and Response is application/json
The API also leverages spring-hateoas as a container response where appropriate

## Error States
The common [HTTP Response codes] (https://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html) are used

## The Customer Profile Resource [/v1/customer/profiles]

### Create Customer Profile By User Id [POST /v1/customer/profiles/{userId}]

+ userId (mandatory, string) ... a brief description on the userId comes here

+ Request
	+ Parameters
	
	+ Headers
	
		client-api-key : xxxx
		Authorisation : xxxx
		applicationname : xxx
	
	+ Body
		
		userDetails(mandatory, string) ... a brief description on userDetails section comes here.
		
+ Response 201 (application/json)		

+ Response 400 (application/json)

+ Response 401 (application/json)

+ Response 403 (application/json)

+ Response 500 (application/json)


### Update Customer Profile By User Id [PUT /v1/customer/profiles/{userId}]

+ userId (mandatory, string) ... a brief description on the userId comes here

+ Request
	+ Parameters
	
	+ Headers
	
		client-api-key : xxxx
		Authorisation : xxxx
		applicationname : xxx
	
	+ Body
		
		userDetails(mandatory, string) ... a brief description on userDetails section comes here.
		
+ Response 200 (application/json)		

+ Response 400 (application/json)

+ Response 401 (application/json)

+ Response 403 (application/json)

+ Response 500 (application/json)

### Get Customer Profile By User Id [GET /v1/customer/profiles/{userId}]

+ userId (mandatory, string) ... a brief description on the userId comes here

+ Request
	+ Parameters
	
	+ Headers
	
		client-api-key : xxxx
		Authorisation : xxxx
		applicationname : xxx
	
	+ Body		
		
+ Response 200 (application/json)		
	
	+ Body	
		
		{  
		   "firstName":"abc",
		   "lastName":"xyz",
		   "dob":"01-01-1970",
		   "addresslist":[  
		      {  
		         "type":"Home",
		         "value":"sample_home_address"
		      },
		      {  
		         "type":"Home",
		         "value":"sample_office_address"
		      },
		      {  
		         "type":"Home",
		         "value":"sample@sample.com"
		      }
		   ]
		}

+ Response 400 (application/json)

+ Response 401 (application/json)

+ Response 403 (application/json)

+ Response 500 (application/json)

### Delete Customer Profile By User Id [DELETE /v1/customer/profiles/{userId}]

+ userId (mandatory, string) ... a brief description on the userId comes here

+ Request
	+ Parameters
	
	+ Headers
	
		client-api-key : xxxx
		Authorisation : xxxx
		applicationname : xxx
	
	+ Body		
	
		
+ Response 200 (application/json)		

+ Response 400 (application/json)

+ Response 401 (application/json)

+ Response 403 (application/json)

+ Response 500 (application/json)
