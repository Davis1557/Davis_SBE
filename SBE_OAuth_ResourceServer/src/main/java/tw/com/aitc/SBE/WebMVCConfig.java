package tw.com.aitc.SBE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebMVCConfig.class);

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// Add this interceptor for I/O log
		registry.addInterceptor(new HandlerInterceptorAdapter() {
			@Override
			public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
				ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
				StringBuilder sb = new StringBuilder();
				sb
						.append("\n==================================================")
						.append("\nReceive Request")
						.append("\nURI: ").append(req.getRequestURI())
						.append("\nMethod: ").append(req.getMethod())
						.append("\nHeaders: ");
				Collections
						.list(req.getHeaderNames())
						.forEach(h -> sb.append("\n\t").append(h).append(": ").append(req.getHeader(h)));
//				sb
//						.append("\nBody:\n")
//						.append(IOUtils.toString(req.getInputStream(), req.getCharacterEncoding()))
//						.append("\n==================================================");
				LOGGER.info(sb.toString());
				return true;
			}

			@Override
			public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
				ContentCachingResponseWrapper resp = new ContentCachingResponseWrapper(response);
				StringBuilder sb = new StringBuilder();
				sb
						.append("\n==================================================")
						.append("\nSend Response")
						.append("\nHeaders: ");
				resp
						.getHeaderNames()
						.forEach(h -> sb.append("\n\t").append(h).append(": ").append(resp.getHeader(h)));
//				sb
//						.append("\nBody:\n")
//						.append(IOUtils.toString(resp.getContentInputStream(), resp.getCharacterEncoding()))
//						.append("\n==================================================");
				LOGGER.info(sb.toString());
				resp.copyBodyToResponse();
			}
		}).order(Ordered.HIGHEST_PRECEDENCE)
		;
	}
}
