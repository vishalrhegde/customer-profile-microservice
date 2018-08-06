package au.com.vishal.customerprofilemicroservice.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import au.com.vishal.customerprofilemicroservice.client.Address;
import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerProfileControllerTests {

	@Autowired
    private MockMvc mockMvc;	

	/**
	 * Testing Profile Creation Success scenario by making a Http POST request to the API and verifying 
	 * the expected HTTP status code - 201 Created and response body.
	 *  
	 * @throws Exception
	 */
	@Test
	public void testProfileCreationSuccess() throws Exception{
		String resourceUrl = "/v1/customer/profiles/1234";
		String requestBody = "{\"firstName\":\"abc\", \"lastName\":\"xyz\"}";
		this.mockMvc.perform(post(resourceUrl).content(requestBody)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isCreated());		
	}
	
	/**
	 * Testing Profile Creation Failure scenario by making a Http POST request and 
	 * setting requestBody as empty string and verifying the expected HTTP Status code - 400 Bad Request.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testProfileCreationFailure_BadRequest() throws Exception{
		String resourceUrl = "/v1/customer/profiles/1234";
		String requestBody = "";
		this.mockMvc.perform(post(resourceUrl).content(requestBody))
			.andDo(print())
			.andExpect(status().isBadRequest());		
	}
	
	/**
	 * Testing Profile retrieval scenario.
	 * As a first step, Http POST is made to create the profile and then using the same userId Http GET 
	 * request is sent. Verification is done by verifying the HTTP status code - 200 and
	 * parsing the response body using json path.
	 * @throws Exception
	 */
	@Test
	public void testGetProfileSuccess() throws Exception{
		String resourceUrl = "/v1/customer/profiles/1234";
		String testFirstName = "abc";
		String testLastName = "xyz";
		String testDob = "01-01-1970";
		
		Address addrHome = new Address("Home", "sample_home_address");
		Address addrOffice = new Address("Home", "sample_office_address");
		Address addrEmail = new Address("Home", "sample@sample.com");
		List<Address> addrList = new ArrayList<Address>();
		addrList.add(addrHome);
		addrList.add(addrOffice);
		addrList.add(addrEmail);
		
		CustomerProfile customerProfile = new CustomerProfile(testFirstName, testLastName, testDob, addrList);
		JSONObject customerProfileJson = new JSONObject(customerProfile);
		this.mockMvc.perform(post(resourceUrl).content(customerProfileJson.toString())
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isCreated());		
		
		
		this.mockMvc.perform(get(resourceUrl)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value(testFirstName))
				.andExpect(jsonPath("$.lastName").value(testLastName))
				.andExpect(jsonPath("$.dob").value(testDob));
	}
	
	@Test
	public void testUpdateProfileSuccess() throws Exception{
		String resourceUrl = "/v1/customer/profiles/1234";
		String requestBody = "{\"firstName\":\"abc\", \"lastName\":\"def\"}";
		this.mockMvc.perform(put(resourceUrl).content(requestBody)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteProfileSuccess() throws Exception{
		String resourceUrl = "/v1/customer/profiles/1234";
		String requestBody = "{\"firstName\":\"abc\", \"lastName\":\"xyz\"}";
		this.mockMvc.perform(delete(resourceUrl).content(requestBody)
					.accept(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isOk());
	}
}
