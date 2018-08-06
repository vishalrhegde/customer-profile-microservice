package au.com.vishal.customerprofilemicroservice.rest;

import java.net.URISyntaxException;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;
import au.com.vishal.customerprofilemicroservice.exception.CustomerProfileMicroserviceException;

public interface CrmClient {
	
	/**
	 * Call CRM End point based on request type.
	 * <br>
	 * Note: For this task, sample data is returned.
	 * 
	 * @param operationType
	 * @param userId
	 * @param userDetails
	 * @return ResponseEntity<String> containing {@link CustomerProfile} for GET request.
	 * For POST, PUT and DELETE requests, this returns either success or failure text.
	 * 
	 * @throws CustomerProfileMicroserviceException
	 */
	public ResponseEntity<String> callCrmEndpoint(HttpMethod operationType
													,String userId
													,String userDetails) throws CustomerProfileMicroserviceException;
}
