package au.com.vishal.customerprofilemicroservice.service;

import org.springframework.http.HttpStatus;

import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;
import au.com.vishal.customerprofilemicroservice.exception.CustomerProfileMicroserviceException;

public interface CrmService {

	/**
	 * 
	 * @param userId
	 * @param userDetails
	 * @return {@link HttpStatus} code. For example:
	 * <br> 201 - Created.
	 * <br> 409 - Conflict. Record already Exists.
	 * <br> 500 - Internal Server Error.
	 * 
	 * @throws CustomerProfileMicroserviceException
	 */
	public HttpStatus createProfile(String userId, String userDetails) throws CustomerProfileMicroserviceException;
	
	/**
	 * 
	 * @param userId
	 * @param userDetails
	 * @return {@link HttpStatus} code. For example:
	 * <br> 200 - OK.
	 * <br> 404 - Not Found
	 * <br> 500 - Internal Server Error.
	 * @throws CustomerProfileMicroserviceException
	 */
	public HttpStatus updateProfile(String userId, String userDetails) throws CustomerProfileMicroserviceException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws CustomerProfileMicroserviceException
	 */
	public HttpStatus deleteProfile(String userId) throws CustomerProfileMicroserviceException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 * @throws CustomerProfileMicroserviceException
	 */
	public CustomerProfile getProfile(String userId) throws CustomerProfileMicroserviceException;
}
