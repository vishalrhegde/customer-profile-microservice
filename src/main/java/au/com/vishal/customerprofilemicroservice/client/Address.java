package au.com.vishal.customerprofilemicroservice.client;

/**
 * Holds different type of Customer's address
 * <br>
 * E.g. Home address, Office address, email address etc.
 * 
 * @author Vishal
 *
 */
public class Address {
	
	private String type;
	private String value;
	
	public Address(){
		
	}

	public Address(String type, String value){
		this.type = type;
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
