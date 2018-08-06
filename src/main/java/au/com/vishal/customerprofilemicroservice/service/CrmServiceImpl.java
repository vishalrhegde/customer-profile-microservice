package au.com.vishal.customerprofilemicroservice.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;
import au.com.vishal.customerprofilemicroservice.exception.CustomerProfileMicroserviceException;
import au.com.vishal.customerprofilemicroservice.rest.CrmClient;

@Service
public class CrmServiceImpl implements CrmService{

	private static Logger logger = LoggerFactory.getLogger(CrmServiceImpl.class);
	
	@Autowired
	private CrmClient crmClient;
	
	@Override
	public HttpStatus createProfile(String userId, String userDetails) throws CustomerProfileMicroserviceException {
		HttpStatus httpStatus = null;
		
		ResponseEntity<String> response = crmClient.callCrmEndpoint(HttpMethod.POST, userId, userDetails);
		
		if(response == null || response.getBody() == null){
			logger.error("Error occured. NULL response is received.");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}else{
			if(response.getStatusCodeValue() == HttpStatus.CREATED.value()){
				logger.info("Create Profile call is successful.");
				
			}else{
				logger.error("Create Profile call is unsuccessful. Response status:" + response.getStatusCodeValue());				
			}
			httpStatus = response.getStatusCode();
		}
		return httpStatus;
	}

	@Override
	public HttpStatus updateProfile(String userId, String userDetails) throws CustomerProfileMicroserviceException {
		HttpStatus httpStatus = null;	
		
		ResponseEntity<String> response = crmClient.callCrmEndpoint(HttpMethod.PUT, userId, userDetails);
		
		if(response == null || response.getBody() == null){
			logger.error("Error occured. NULL response is received.");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}else{
			if(response.getStatusCodeValue() == HttpStatus.OK.value()){
				logger.info("Update Profile call is successful.");
				
			}else{
				logger.error("Update Profile call is unsuccessful. Response status:" + response.getStatusCodeValue());				
			}
			httpStatus = response.getStatusCode();
		}
		return httpStatus;
	}
	

	@Override
	public HttpStatus deleteProfile(String userId) throws CustomerProfileMicroserviceException {
		HttpStatus httpStatus = null;	
		
		ResponseEntity<String> response = crmClient.callCrmEndpoint(HttpMethod.DELETE, userId, null);
		
		if(response == null || response.getBody() == null){
			logger.error("Error occured. NULL response is received.");
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}else{
			if(response.getStatusCodeValue() == HttpStatus.OK.value()){
				logger.info("Delete Profile call is successful.");
				
			}else{
				logger.error("Delete Profile call is unsuccessful. Response status:" + response.getStatusCodeValue());				
			}
			httpStatus = response.getStatusCode();
		}
		return httpStatus;
	}

	@Override
	public CustomerProfile getProfile(String userId) throws CustomerProfileMicroserviceException {
		
		CustomerProfile customerProfile = null;
	
		ResponseEntity<String> response = crmClient.callCrmEndpoint(HttpMethod.GET, userId, null);
		
		if(response == null || response.getBody() == null){
			logger.error("Error occured. NULL response is received.");	
			
		}else{
			if(response.getStatusCodeValue() == HttpStatus.OK.value()){
				logger.info("GET Profile call is successful.");				
				ObjectMapper mapper = new ObjectMapper();				
				try {
					customerProfile = mapper.readValue(response.getBody(), CustomerProfile.class);
				} catch (IOException e) {
					logger.error(e.getMessage());			
					throw new CustomerProfileMicroserviceException(e.getMessage(), e);
				}
			}else{
				logger.error("GET Profile call is unsuccessful. Response status:" + response.getStatusCodeValue());				
			}
		}		
		return customerProfile;		
	}

}
