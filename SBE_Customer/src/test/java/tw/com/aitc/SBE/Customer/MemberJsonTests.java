package tw.com.aitc.SBE.Customer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class MemberJsonTests {

	private Customer customer;

	@Autowired
	ApplicationContext context;

	@Autowired
	private JacksonTester<Customer> jackson;

	@BeforeEach
	void before() {
		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
		System.out.println("===== ===== ===== ===== ===== =====");

		customer = new Customer("1", "MockCustomer");
	}

	@Test
	void deserializeJson() throws Exception {
		String str = "{\"id\":\"1\",\"name\":\"MockCustomer\"}";
		assertThat(jackson.parse(str))
				.isInstanceOf(Customer.class)
				.isEqualTo(customer);
	}

	@Test
	void serializeJson() throws Exception {
		assertThat(jackson.write(customer))
				.isEqualToJson(new ClassPathResource("json/Customer.1.MockCustomer"))
				.hasJsonPath("@.id");
	}
}
