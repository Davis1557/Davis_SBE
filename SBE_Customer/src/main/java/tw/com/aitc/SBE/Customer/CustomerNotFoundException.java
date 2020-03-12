package tw.com.aitc.SBE.Customer;

public class CustomerNotFoundException extends RuntimeException {

	public CustomerNotFoundException(String id) {
		super("Customer Not Found With Id : " + id);
	}
}
