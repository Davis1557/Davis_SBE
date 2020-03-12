package tw.com.aitc.SBE.Customer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CusExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity handleCustomerNotFound(CustomerNotFoundException e) {
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(e.getMessage());
	}
}
