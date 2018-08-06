package au.com.vishal.customerprofilemicroservice.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Register a Spring Security Initializer and its underlying configuration class.
 * 
 * @author Vishal
 *
 */
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	public SecurityInitializer(){
		super(SecurityConfig.class);
	}
}
