package au.com.vishal.customerprofilemicroservice.controller;

import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import au.com.vishal.customerprofilemicroservice.client.CustomerProfileResource;
import au.com.vishal.customerprofilemicroservice.controller.util.Constants;
import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;
import au.com.vishal.customerprofilemicroservice.service.CrmService;

/**  
 * Exposes REST APIs/End points for Create, Update, Delete and Get Customer's Profile information 
 * as per the API Contract definition file. * 
 *
 */
@RestController
public class CustomerProfileController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerProfileController.class);
	
	@Autowired
	private CrmService crmService;
	
	/**
	 * 
	 * @param userId
	 * @param requestBody
	 * @param requestHeader
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.POST
					,value=Constants.PROFILE_PATH
					,produces=MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<String> createProfile(@PathVariable String userId
												,@RequestBody String requestBody
												,@RequestHeader Map<String, String> requestHeader) throws Exception{
		HttpStatus httpStatus = null;		
		
		logger.debug("Profile creation request received for userId: " + userId);
		if(isBadRequest(userId, requestBody)){ 
			httpStatus = HttpStatus.BAD_REQUEST;			
			logger.error("Request Body is empty or contains invalid data " + requestBody);
		}
		else{
			httpStatus = crmService.createProfile(userId, populateUserDetails(requestBody));
			
		}
		return new ResponseEntity<String>(httpStatus);
	}
	
	/**
	 * 
	 * @param userId
	 * @param requestBody
	 * @param requestHeader
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method=RequestMethod.GET
								,value=Constants.PROFILE_PATH
								,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerProfileResource> findProfile(@PathVariable String userId									
									,@RequestHeader Map<String, String> requestHeader) throws Exception{									
		HttpStatus httpStatus = null;
		CustomerProfileResource customerProfileResource = null;		
		
		logger.debug("Get Profile request received for userId: " + userId);
		
		if(isBadRequest(userId)){ 
			httpStatus = HttpStatus.BAD_REQUEST;
			logger.error("Invalid userId " + userId);
		}
		else{
			CustomerProfile customerProfile = crmService.getProfile(userId);
			if(customerProfile != null){
				customerProfileResource = new CustomerProfileResource(customerProfile.getFirstName()
																	,customerProfile.getLastName()
																	,customerProfile.getDob()
																	,customerProfile.getAddresslist());
				httpStatus = HttpStatus.OK;
			}else{			
				httpStatus = HttpStatus.NOT_FOUND;
			}
		}		
		return new ResponseEntity<CustomerProfileResource>(customerProfileResource, httpStatus);
	}
	
	/**
	 * 
	 * @param userId
	 * @param requestHeader
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.PUT, value = Constants.PROFILE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateProfile(@PathVariable String userId
														,@RequestBody String requestBody
														,@RequestHeader Map<String, String> requestHeader) throws Exception {
		HttpStatus httpStatus = null;

		logger.debug("Profile update request received for userId: " + userId);
		if (isBadRequest(userId, requestBody)) {
			httpStatus = HttpStatus.BAD_REQUEST;
			logger.error("Request Body is empty or contains invalid data " + requestBody);
		} else {
			httpStatus = crmService.updateProfile(userId, populateUserDetails(requestBody));
		}
		return new ResponseEntity<String>(httpStatus);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = Constants.PROFILE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteProfile(@PathVariable String userId									
									,@RequestHeader Map<String, String> requestHeader)throws Exception{
		
		HttpStatus httpStatus = null;
		logger.debug("Profile delete request received for userId: " + userId);
		if (isBadRequest(userId)) {
			httpStatus = HttpStatus.BAD_REQUEST;
			logger.error("Invalid userId " + userId);
		} else {
			httpStatus = crmService.deleteProfile(userId);
		}
		return new ResponseEntity<String>(httpStatus);
		
	}
	
	private boolean isBadRequest(String userId){
		if(userId == null || userId.length() == 0 ){
			return true;
		}
		
		/*
		 * TODO: Apply more validation for the incoming request.
		 * This includes mandatory firstName and lastName, address, telephone and email formats etc.
		 * return true if all are valid or false for invalid inputs. 
		 */
		return false;
	}
	
	private boolean isBadRequest(String userId, String requestBody){
		if(userId == null || userId.length() == 0 || requestBody == null || requestBody.length() == 0 ){
			return true;
		}
		
		/*
		 * TODO: Apply more validation for the incoming request.
		 * This includes mandatory firstName and lastName, address, telephone and email formats etc.
		 * return true if all are valid or false for invalid inputs. 
		 */
		return false;
	}
	
	private String populateUserDetails(String requestBody){
		/*
		 * TODO: Extract and return the details required for profile creation
		 * from the request body. 
		 * For this task, the assumption is that request body contains all the 
		 * required information for profile creation.  
		 */
		return requestBody;
	}

}
