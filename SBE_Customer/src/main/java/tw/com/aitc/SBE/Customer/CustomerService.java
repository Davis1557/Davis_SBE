package tw.com.aitc.SBE.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer findById(String id) {
		return customerRepository.findById(id).orElse(null);
	}
}
