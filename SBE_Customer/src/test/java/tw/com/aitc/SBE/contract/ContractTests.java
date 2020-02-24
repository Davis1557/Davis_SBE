package tw.com.aitc.SBE.contract;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import tw.com.aitc.SBE.Customer.Customer;
import tw.com.aitc.SBE.Customer.CustomerRestController;
import tw.com.aitc.SBE.Customer.CustomerService;
import tw.com.aitc.SBE.Customer.MainApplication;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@TestPropertySource(properties =
		{"spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
@SpringBootTest(webEnvironment = MOCK, classes = MainApplication.class)
public class ContractTests {

	@MockBean
	CustomerService service;

	@Autowired
	CustomerRestController controller;

	@BeforeEach
	public void before() {
		// Mockito 定義 method 處理
		when(service.findById(matches("\\d{1,3}")))
				.thenAnswer(in -> new Customer(in.getArgument(0), "MockCustomer"));

		standaloneSetup(controller);
	}
}
