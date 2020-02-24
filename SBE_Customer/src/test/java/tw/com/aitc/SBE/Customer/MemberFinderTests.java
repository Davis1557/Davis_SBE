package tw.com.aitc.SBE.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(CustomerFinder.class)
public class MemberFinderTests {
	@Autowired
	ApplicationContext context;

	@Autowired
	CustomerFinder finder;

	@Autowired
	MockRestServiceServer mockServer;

	@Test
	void findCustomerById() throws Exception {
		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
		System.out.println("===== ===== ===== ===== ===== =====");

		// 模擬 Server 處理
		mockServer.expect(requestTo("http://localhost:8080/customer/1"))
				.andRespond(withSuccess(
						new ClassPathResource("json/Customer.1.MockCustomer"),
						MediaType.APPLICATION_JSON)
				);

		// Client 發動
		Customer customer = finder.findCustomerById("1");

		assertEquals("1", customer.getId());
		assertEquals("MockCustomer", customer.getName());
	}
}
