package tw.com.aitc.SBE.httpSession;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessionRestController.class)
public class SessionRestControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void testSession() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setMaxInactiveInterval(300);

		System.out.println("Session is New ? " + session.isNew());

		mockMvc.perform(
				post("/hello")
						.contentType(APPLICATION_JSON)
						.characterEncoding("UTF8")
						.session(session)
						.content("{\"word\" : \"x1\"}")
						.accept(APPLICATION_JSON)
		)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk())
				.andReturn();

		System.out.println("Session is New ? " + session.isNew());

//		mockMvc.perform(
//				post("/hello")
//						.contentType(APPLICATION_JSON)
//						.characterEncoding("UTF8")
//						.session(session)
//						.content("{\"word\" : \"x2\"}")
//						.accept(APPLICATION_JSON)
//		)
//				.andDo(MockMvcResultHandlers.print())
//				.andExpect(status().isOk())
//				.andReturn();
//
//		System.out.println("Session is New ? " + session.isNew());
	}
}
