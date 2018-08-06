package au.com.vishal.customerprofilemicroservice.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import au.com.vishal.customerprofilemicroservice.data.CustomerProfile;

/**
 * Resource for {@link CustomerProfile}. Based on the model of 
 * <a href="https://docs.spring.io/spring-hateoas/docs/current/reference/html/">Spring Hateoas</a>
 * 
 * This is capable of providing the required hypermedia that links to other resources.
 * 
 * @author Vishal
 *
 */
public class CustomerProfileResource extends ResourceSupport{
	
	@JsonCreator
	public CustomerProfileResource(String firstName
									,String lastName
									,String dob
									,List<Address> addressList){
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		setAddresslist(addressList);
		
	}
	
	@JsonProperty
	private String firstName;
	
	@JsonProperty
	private String lastName;
	
	@JsonProperty
	private String dob;
	
	@JsonProperty
	private List<Address> addresslist = new ArrayList<Address>();

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDob() {
		return dob;
	}

	public List<Address> getAddresslist() {
		List<Address> returnList = new ArrayList<Address>();
		for(Address addr : this.addresslist){
			returnList.add(addr);
		}
		return returnList;
	}

	public void setAddresslist(List<Address> addresslist) {
		for(Address addr : addresslist){
			this.addresslist.add(addr);
		}
	}	
	
}
