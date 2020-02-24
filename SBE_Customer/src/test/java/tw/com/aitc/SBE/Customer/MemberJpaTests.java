package tw.com.aitc.SBE.Customer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberJpaTests {
	@Autowired
	ApplicationContext context;

	@Autowired
	CustomerRepository repository;

	@Autowired
	TestEntityManager manager;

	@Test
	void findAll() throws Exception {
		// 從 ApplicationContext 取得目前 Bean 的情況
		Map<String, Object> beansOfType = context.getBeansOfType(Object.class);
		System.out.println("===== Quantity: " + beansOfType.size() + " =====");
		System.out.println("===== ===== ===== ===== ===== =====");

		// 在測試儲存庫放進資料
		manager.persist(new Customer("1", "MockCustomer"));

		assertThat(repository.findAll())
				.doesNotContainNull()

				.element(0)
				.isInstanceOf(Customer.class);
	}
}
