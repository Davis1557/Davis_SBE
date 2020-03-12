package tw.com.aitc.SBE.httpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class SessionRestController {
	// Session 儲存方式看設定檔
	@Autowired
	ServletContext servletContext;

	@Autowired
	ApplicationContext applicationContext;


	@GetMapping(value = "/test/{word}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String testWord(HttpSession session, @PathVariable String word) {
		System.out.println("Session :");
		//  列出 Session 儲存的 Attribute
		Collections.list(session.getAttributeNames())
				.forEach(a -> System.out.println(a + " : " + session.getAttribute(a)));
		System.out.println(" ----------------- ");

		//  取出 oldWord 資料
		Object oldWord = session.getAttribute("oldWord");
		//  newWord 是 oldWord + 傳入的 word
		String newWord = oldWord == null ? word : oldWord + word;
		//  保存 newWord
		session.setAttribute("oldWord", newWord);

		//  回傳 newWord
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
