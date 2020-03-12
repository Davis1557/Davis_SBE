package tw.com.aitc.SBE.HTTP_INVOKER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;

@Controller
public class HttpInvokerController {

	@Bean
	public HelloService helloService() {
		return new HelloWorld();
	}

	// 在 Spring MVC中，會對 Bean Name 做解析
	// "/" 開頭的命名會有一個對應的 URL Mapping
	@Bean(name = "/helloService")
	@Autowired
	public HttpInvokerServiceExporter httpHelloService(HelloService helloService) {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(HelloService.class);
		exporter.setService(helloService);
		// 預設的 Pool ，可以 Extend SimpleHttpInvokerRequestExecutor 改寫
		// exporter.setRemoteInvocationExecutor(new DefaultRemoteInvocationExecutor());
		return exporter;
	}


	@Bean(name = "/ww")
	public HttpRequestHandler ww() {
		return new WWInvoker();
	}
}
