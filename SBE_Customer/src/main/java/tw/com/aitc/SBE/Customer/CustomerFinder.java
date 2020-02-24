package tw.com.aitc.SBE.Customer;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class CustomerFinder {

	RestTemplate restTemplate;

	public CustomerFinder(RestTemplateBuilder builder) {
		this.restTemplate = builder.build();
	}

	public Customer findCustomerById(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);

		HttpEntity entity = new HttpEntity(headers);
		ResponseEntity<Customer> result
				= restTemplate.exchange("http://localhost:8080/customer/" + id,
				GET, entity, Customer.class
		);
		return result.getBody();
	}
}
