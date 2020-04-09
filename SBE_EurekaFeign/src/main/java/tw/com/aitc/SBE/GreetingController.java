package tw.com.aitc.SBE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {

	@Autowired
	private Greeting greeting;

	@GetMapping(value = "/feign/greet/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> feignGreeting(@PathVariable String name) {
		return greeting.greet(name);
	}
}
