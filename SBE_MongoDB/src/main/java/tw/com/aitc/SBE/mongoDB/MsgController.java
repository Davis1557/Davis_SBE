package tw.com.aitc.SBE.mongoDB;

import io.micrometer.core.annotation.Timed;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/msg")
public class MsgController {

	@Autowired
	public MsgService service;

	@Timed(value = "davis.test.controller.find.query")
	@PostMapping(value = "/find/query",
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Map> findByQuery(@RequestBody String query) {
		return service.findByQuery(query);
	}

	@Timed("davis.test.controller.insert.string")
	@PostMapping(value = "/insert/string",
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertBodyString(@RequestBody String body) {
		return service.insertBodyString(body);
	}

	@PostMapping(value = "/insert/json")
	public String insertBodyJson(@RequestBody Document document) {
		return service.insertBodyJson(document);
	}

	@PutMapping(value = "/update/query")
	public String updateByQuery(@RequestBody String query) {
		return service.updateByQuery(query);
	}
}