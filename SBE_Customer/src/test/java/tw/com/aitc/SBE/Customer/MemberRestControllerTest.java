package tw.com.aitc.SBE.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerRestController.class)
class MemberRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ApplicationContext context;

	@MockBean
	CustomerService service;

	@Test
	void getOneCustomer() throws Exception {
		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
		System.out.println("===== ===== ===== ===== ===== =====");

		// Mockito 定義 method 處理
		when(service.findById(matches("\\d{1,3}")))
				.thenAnswer(in -> new Customer(in.getArgument(0), "MockCustomer"));

		// 模擬 Client 發送，並斷言回應
		mockMvc.perform(get("/customer/1")
				.accept(APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":\"1\",\"name\":\"MockCustomer\"}"));
	}
}