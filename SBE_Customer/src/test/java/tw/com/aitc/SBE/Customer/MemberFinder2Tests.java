package tw.com.aitc.SBE.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import static org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode.LOCAL;

@TestPropertySource(properties =
		{"spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(ids = {"tw.com.aitc:SBE:+:stubs:8080"}, stubsMode = LOCAL)
public class MemberFinder2Tests {
	@MockBean
	CustomerService service;

	@Autowired
	CustomerFinder finder;

	//	@Test
	void findCustomerById() throws Exception {
		// Mockito 定義 method 處理
		when(service.findById(matches("\\d{1,3}")))
				.thenAnswer(in -> new Customer(in.getArgument(0), "MockCustomer"));

		// Client 發動
		Customer customer = finder.findCustomerById("1");

		assertEquals("1", customer.getId());
	}
}
