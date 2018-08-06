package au.com.vishal.customerprofilemicroservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import au.com.vishal.customerprofilemicroservice.client.Address;
import au.com.vishal.customerprofilemicroservice.controller.util.Constants;
import au.com.vishal.customerprofilemicroservice.controller.util.CrmEndpointResource;
import au.com.vishal.customerprofilemicroservice.controller.util.RequestHeader;
import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;
import au.com.vishal.customerprofilemicroservice.exception.CustomerProfileMicroserviceException;

/**
 * 
 * @author Vishal
 *
 */
@Component
public class CrmClientImpl implements CrmClient{
	
	private static final Logger logger = LoggerFactory.getLogger(CrmClientImpl.class);
	
	private final RestTemplate restTemplate;
	
	@Autowired
	private Environment env;
	
	public CrmClientImpl(RestTemplateBuilder restTemplateBuilder){
		this.restTemplate = restTemplateBuilder.build();
	}
	
	
	@Override
	public ResponseEntity<String> callCrmEndpoint(HttpMethod operationType
													,String userId
													,String userDetails) throws CustomerProfileMicroserviceException{
		ResponseEntity<String> responseEntity = null;		
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.set(RequestHeader.AUTHORISATION.value(), env.getRequiredProperty(Constants.CRM_AUTH_KEY));
		requestHeaders.set(RequestHeader.API_CLIENT_KEY.value(), env.getRequiredProperty(Constants.API_SUBSCRIBER_KEY));
				
		String crmReqUrl = generateRequestUrl(operationType, userId);
		/*
		 * HttpEntity<String> requestEntity = new HttpEntity<String>(populateRequestBody(userDetails), requestHeaders);
		 * responseEntity = restTemplate.exchange(crmReqUrl, operationType, requestEntity, String.class);
		*/
		responseEntity = returnSampleCrmResponse(operationType, crmReqUrl);
		
		return responseEntity;
	}
	
	/**
	 * Use the user details information received from the UI client and prepare 
	 * request body as per CRM requirement.
	 * For this task, returning the userDetails as is.
	 * @param userDetails
	 * @return requestBody required to make CRM api call.
	 */
	private String populateRequestBody(String userDetails){
		return userDetails;
	}
	
	
	private String generateRequestUrl(HttpMethod operationType, String reqParam){		
		String serverUrl = env.getRequiredProperty("crm.server.url");		
		return serverUrl + CrmEndpointResource.PROFILE_RESOURCE.value() + "/" + reqParam;		
	}
	
	/**
	 * This is used to return sample response based on input request.
	 * <br>
	 * The sample response is created based on the response details available 
	 * in the CRM API contract definition file.
	 * 
	 * @param operationType
	 * @return ResponseEntity<String> containing Http Status and Response body.
	 * @throws CustomerProfileMicroserviceException 
	 */
	private ResponseEntity<String> returnSampleCrmResponse(HttpMethod operationType, String reqUrl) throws CustomerProfileMicroserviceException{
		
		ResponseEntity<String> responseEntity = null;
		URI reqUri = null;	
		try {
			reqUri = new URI(reqUrl);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());			
			throw new CustomerProfileMicroserviceException(e.getMessage(), e);
		}
	
		if(operationType.equals(HttpMethod.POST)){
			responseEntity = ResponseEntity.created(reqUri)
									.header("ResponseHeader1", "HeaderValue1")
									.body("success");
		}
		else if(operationType.equals(HttpMethod.GET)){
			Address addrHome = new Address("Home", "sample_home_address");
			Address addrOffice = new Address("Home", "sample_office_address");
			Address addrEmail = new Address("Home", "sample@sample.com");
			List<Address> addrList = new ArrayList<Address>();
			addrList.add(addrHome);
			addrList.add(addrOffice);
			addrList.add(addrEmail);
			
			CustomerProfile customerProfile = new CustomerProfile("abc", "xyz", "01-01-1970", addrList);
			JSONObject customerProfileJson = new JSONObject(customerProfile);
			
			responseEntity = ResponseEntity.ok()
									.header("ResponseHeader1", "HeaderValue1")
									.body(customerProfileJson.toString());
		}
		else if(operationType.equals(HttpMethod.PUT)){
			responseEntity = ResponseEntity.ok()
											.header("ResponseHeader1", "HeaderValue1")
											.body("success");
		}
		else if(operationType.equals(HttpMethod.DELETE)){
			responseEntity = ResponseEntity.ok()
											.header("ResponseHeader1", "HeaderValue1")
											.body("success");
		}else{
			//TODO: Throw Unsupported operation exception and send appropriated response to the caller.
		}
		
		return responseEntity;
	}
	
}
