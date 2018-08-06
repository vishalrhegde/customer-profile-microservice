package au.com.vishal.customerprofilemicroservice.controller.util;

public enum RequestHeader {
	AUTHORISATION("Authorisation")
	,API_CLIENT_KEY("api-client-key");
	
	private String value;
	
	private RequestHeader(String value){
		this.value = value;
	}
	
	public String value(){
		return value;
	}
	
	
}
