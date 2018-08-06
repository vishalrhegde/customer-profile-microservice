package au.com.vishal.customerprofilemicroservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.vishal.customerprofilemicroservice.controller.CustomerProfileController;
import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {

	@Autowired
	private CustomerProfileController customerProfileController;
	
	@Test
    public void contexLoads() throws Exception {
        assertThat(customerProfileController).isNotNull();
    }
}
