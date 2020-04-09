package tw.com.aitc.SBE.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer findById(String id) {
		return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
	}

	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}
}
