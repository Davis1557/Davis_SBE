package tw.com.aitc.SBE.Customer;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class MemberRestController2Test {

	@Autowired
	ApplicationContext context;

	@Mock
	CustomerService service;

	@InjectMocks
	CustomerRestController controller;

	@BeforeEach
	public void before() {
		RestAssuredMockMvc.standaloneSetup(controller);
	}

	@Test
	void getOneCustomer() throws Exception {
		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
		System.out.println("===== ===== ===== ===== ===== =====");

		// Mockito 定義 method 處理
		when(service.findById(matches("\\d{1,3}")))
				.thenAnswer(in -> new Customer(in.getArgument(0), "MockCustomer"));

		given()
				.when()
				.get("/customer/1")
				.then()
				.log().all()
				.expect(status().isOk());
	}
}
