package au.com.vishal.customerprofilemicroservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import au.com.vishal.customerprofilemicroservice.exception.CustomerProfileMicroserviceException;

/**
 * Handle Errors/Exception thrown from Controller class.
 * 
 * @author Vishal
 *
 */
@Controller
public class ExceptionHandlingController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(value=HttpStatus.CONFLICT,
    		reason="Data integrity violation - Duplicate data")  
	public void conflict(){
		
	}
	
	@ExceptionHandler(CustomerProfileMicroserviceException.class)
	public String handleCustomerProfileMicroserviceException(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "Error Occurred");
		responseJson.put("status", "error");
		return responseJson.toString();
		 
	}

	
	@ExceptionHandler(Exception.class)
	public String handleError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "Error Occurred");
		responseJson.put("status", "error");
		return responseJson.toString();
		 
	}
}
