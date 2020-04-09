package tw.com.aitc.SBE;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GreetingController {

	@GetMapping(value = "/greet/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> greeting(@PathVariable String name) {
		return ResponseEntity.ok("Hello " + name);
	}

	@GetMapping(value = "/say/{word}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> saying(@PathVariable String word) {
		return ResponseEntity.ok("Say: " + word);
	}
}
