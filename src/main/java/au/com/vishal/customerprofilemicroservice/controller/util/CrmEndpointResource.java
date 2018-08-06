package au.com.vishal.customerprofilemicroservice.controller.util;

public enum CrmEndpointResource {
	PROFILE_RESOURCE("/v1/customer/profiles");
	
	private String resource;
	
	private CrmEndpointResource(String resource){
		this.resource = resource;
	}
	
	public String value(){
		return resource;
	}
}
