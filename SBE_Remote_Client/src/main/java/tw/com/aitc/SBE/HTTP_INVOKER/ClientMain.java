package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@SpringBootApplication
public class ClientMain {
	public static void main(String[] args) {
		SpringApplication.run(ClientMain.class, args);
	}

	@Bean
	public HttpInvokerProxyFactoryBean helloServiceInvoker() {
		HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
		invoker.setServiceUrl("http://localhost:8080/helloService");
		invoker.setServiceInterface(HelloService.class);
		// 預設的 Pool ，可以 Extend SimpleHttpInvokerRequestExecutor 改寫
		// invoker.setHttpInvokerRequestExecutor(new SimpleHttpInvokerRequestExecutor());
		return invoker;
	}
}
