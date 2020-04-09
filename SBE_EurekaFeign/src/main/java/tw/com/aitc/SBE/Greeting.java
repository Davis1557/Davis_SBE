package tw.com.aitc.SBE;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("SBE-Eureka-Service")
public interface Greeting {

	@GetMapping(value = "/greet/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	ResponseEntity<String> greet(@PathVariable String name);
}
