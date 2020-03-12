package tw.com.aitc.SBE.HTTP_INVOKER;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloServiceTests {

	@Autowired
	HelloService helloService;

	@Test
	void sayHello() {
		// 動態產生的 Class
		System.out.println(helloService.getClass().getName());

		System.out.println("Remote Hello : " + helloService.sayHello());
	}
}
