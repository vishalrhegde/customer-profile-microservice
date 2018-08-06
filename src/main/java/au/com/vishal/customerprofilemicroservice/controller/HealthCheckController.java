package au.com.vishal.customerprofilemicroservice.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to return status of the microservice.
 * 
 * @author Vishal
 *
 */
@RestController
@RequestMapping("/HealthCheck")
@Order(value=Ordered.LOWEST_PRECEDENCE)
public class HealthCheckController {

	/**
	 * Sample health check API.
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method={RequestMethod.GET, RequestMethod.HEAD})
	public String getHealthCheck(HttpServletResponse response) throws Exception{
		response.setHeader("VERSION", "V1");
		response.setHeader("BUILD NUMBER", "0.0.1");
		return "1";
	}
}
