package tw.com.aitc.SBE.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CustomerRestController {

	@Autowired
	private CustomerService service;

	@GetMapping(value = "/customer/{id}")
	@ResponseBody
	public Customer get(@PathVariable String id) {
		return service.findById(id);
	}

	@PostMapping(value = "/customer",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> post(@RequestBody Customer customer) {
		return ResponseEntity.ok(service.save(customer));
	}
}
