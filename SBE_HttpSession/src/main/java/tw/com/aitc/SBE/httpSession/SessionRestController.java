package tw.com.aitc.SBE.httpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
@Transactional
@RequestMapping(value = "/")
public class SessionRestController {

	@GetMapping(value = "/test/{word}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String testWord(HttpSession session, @PathVariable String word) {
		System.out.println("Session :");
		Collections.list(session.getAttributeNames())
				.forEach(a -> System.out.println(a + " : " + session.getAttribute(a)));

		Object preWord = session.getAttribute("preWord");
		String newWord = preWord == null ? word : preWord + word;
		session.setAttribute("preWord", newWord);

		return newWord;
	}

	/////////////////////////////////////////////////////////////////////////////////////

	@PostMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, String> hello(HttpSession session, @RequestBody Map<String, String> body) {
		System.out.println("Session :");
		Collections.list(session.getAttributeNames())
				.forEach(a -> System.out.println(a + " : " + session.getAttribute(a)));

		String word = body.getOrDefault("word", "");
		Object preWord = session.getAttribute("preWord");
		String newWord = preWord == null ? word : preWord + word;
		session.setAttribute("preWord", newWord);

		body.clear();
		body.put("hello", "Hello " + newWord);
		return body;
	}
}
