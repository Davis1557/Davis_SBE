package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

@Configuration
@SpringBootApplication
public class ClientMain {
	public static void main(String[] args) {
		SpringApplication.run(ClientMain.class, args);
	}

	@Bean
	public HttpInvokerProxyFactoryBean helloService() {
		HttpInvokerProxyFactoryBean client = new HttpInvokerProxyFactoryBean();
		client.setServiceUrl("http://localhost:8080/helloService");
		client.setServiceInterface(HelloService.class);
		return client;
	}
}
