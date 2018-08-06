package au.com.vishal.customerprofilemicroservice.data;

import java.util.ArrayList;
import java.util.List;

import au.com.vishal.customerprofilemicroservice.client.Address;

/**
 * This Bean represents all the attributes that is part of the Customer Profile.
 * 
 * @author Vishal
 *
 */
public class CustomerProfile {
	
	public CustomerProfile(){
		
	}
	
	public CustomerProfile(String firstName, String lastName, String dob, List<Address> addresslist){
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;	
		setAddresslist(addresslist);		
	}
	
	private String firstName;	
	private String lastName;	
	private String dob;	
	private List<Address> addresslist = new ArrayList<Address>();

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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
