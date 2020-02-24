package tw.com.aitc.SBE.Customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestPropertySource;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;

@TestPropertySource(properties =
		{"spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"})
// 沒用到 Spring Web
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MemberServiceTests {

	@Autowired
	ApplicationContext context;

	@MockBean
	CustomerRepository repository;

	@Autowired
	CustomerService service;

	// 目標要測 CustomerService 的 findById()
	// findById() 會用到 CustomerRepository
	// 但它不是這個 Test 的受測目標
	// 不必要走實際的處理過程
	@Test
	void findMoreCustomers(TestInfo testInfo) {
		// TestInfo 可以取得一些測試資訊
		System.out.println(testInfo.getTestMethod().get().getName());

		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
//		beansOfType
//				.entrySet()
//				.stream()
//				.map(entry -> entry.getValue().getClass().getName())
//				.sorted()
//				.forEach(System.out::println);
		System.out.println("===== ===== ===== ===== ===== =====");

		// Mockito 定義 method 處理
		when(repository.findById(matches("\\d{1,3}")))
				.thenAnswer(in -> Optional.of(new Customer(in.getArgument(0), "MockCustomer")));

		// 呼叫 Service 的 findById()，裡面使用的 CustomerRepository 已 Autowired 成 MockBean
		Customer customer = service.findById("3");

		// 斷言檢查回應
		assertEquals("3", customer.getId());
		assertEquals("MockCustomer", customer.getName());
	}
}
