package tw.com.aitc.SBE.HTTP_INVOKER;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import tw.com.aitc.SBE.HTTP_INVOKER.HelloService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class HelloServiceTests {

	@Autowired
	ApplicationContext context;

	@Autowired
	HelloService helloService;

	@Test
	void sayHello() {
		System.out.println("Remote Hello : " + helloService.sayHello());
	}
}
