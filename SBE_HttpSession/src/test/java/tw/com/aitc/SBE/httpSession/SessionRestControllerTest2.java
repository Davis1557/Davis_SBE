package tw.com.aitc.SBE.httpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class SessionRestControllerTest2 {

	@Autowired
	RestTemplateBuilder builder;

	private RestTemplate restTemplate;

	@BeforeEach
	void setup() {
		this.restTemplate = builder.build();
	}

	@Test
	void testSession() throws Exception {

		HttpHeaders headers = new HttpHeaders();
		headers.add(CONTENT_TYPE, APPLICATION_JSON_VALUE);

		HttpEntity entity = new HttpEntity("{\"word\" : \"x1\"}", headers);
		ResponseEntity<String> result = restTemplate.exchange(
				"http://localhost:8080/hello",
				POST,
				entity,
				String.class
		);

		ResponseEntity<String> result2 = restTemplate.exchange(
				"http://localhost:8080/hello",
				POST,
				entity,
				String.class
		);
	}
}
