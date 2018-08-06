package au.com.vishal.customerprofilemicroservice;

import static org.mockito.Mockito.mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import au.com.vishal.customerprofilemicroservice.rest.CrmClient;

@Profile("Mock_Client")
@Configuration
@ComponentScan(basePackages={"au.com.vishal.customerprofilemicroservice"})
public class MockTestConfig {

	@Bean
	@Primary
	public CrmClient crmClient(){
		CrmClient client = mock(CrmClient.class);
		return client;
	}
}
