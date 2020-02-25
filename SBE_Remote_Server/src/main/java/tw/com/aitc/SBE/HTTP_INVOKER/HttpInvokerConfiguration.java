package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@Configuration
public class HttpInvokerConfiguration {
	@Bean
	public HelloService helloService() {
		return new HelloWorld();
	}

	@Bean(name = "/helloService")
	@Autowired
	public HttpInvokerServiceExporter httpHelloService(HelloService helloService) {
		HttpInvokerServiceExporter http = new HttpInvokerServiceExporter();
		http.setServiceInterface(HelloService.class);
		http.setService(helloService);
		return http;
	}
}
