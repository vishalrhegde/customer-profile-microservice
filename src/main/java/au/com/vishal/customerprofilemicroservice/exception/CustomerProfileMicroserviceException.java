package au.com.vishal.customerprofilemicroservice.exception;

public class CustomerProfileMicroserviceException extends Exception{

	private static final long serialVersionUID = -8065266163340956878L;
	
	public CustomerProfileMicroserviceException(String message){
		super(message);
	}
	
	public CustomerProfileMicroserviceException(String message, Throwable t){
		super(message, t);
	}
}
