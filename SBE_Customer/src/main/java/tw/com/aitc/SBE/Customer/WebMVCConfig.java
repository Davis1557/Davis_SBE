package tw.com.aitc.SBE.Customer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CusHandlerInterceptor())
				.addPathPatterns("/customer/a");
		registry.addInterceptor(new CusHandlerInterceptor())
				.addPathPatterns("/customer/b");
	}
}
