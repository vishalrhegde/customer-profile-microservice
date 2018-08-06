package au.com.vishal.customerprofilemicroservice.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

/**
 * Spring security configuration.
 * 
 * Implementation could include
 * 
 * 1. HttpSecurity - with BASIC Authentication using LDAP or Active Directory lookup.
 * 2. WebSecurity - with Filters.
 *  
 * @author Vishal
 *
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception{
		
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable();
		/*
		http.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
			.httpBasic();
		*/
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception{
		
//		web.ignoring()
//			.antMatchers("/**/HealthCheck/**")
//			.antMatchers("/SimpleErrorHandler")
//			.antMatchers("/**/stats/**");
		
	
	}
}
