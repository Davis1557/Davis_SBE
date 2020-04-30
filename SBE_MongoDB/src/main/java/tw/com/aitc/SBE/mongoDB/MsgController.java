package tw.com.aitc.SBE.mongoDB;

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
	public MsgRepository repository;

	@PostMapping(value = "/find/query")
	public List<Map> findByQuery(@RequestBody Map<String, String> map) {
		String query = map.get("query");
		return repository.findByQuery(query);
	}

	@PostMapping(value = "/insert/string",
			consumes = MediaType.TEXT_PLAIN_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public String insertBodyString(@RequestBody String body) {
		return repository.insertBodyString(body);
	}

	@PostMapping(value = "/insert/json")
	public String insertBodyJson(@RequestBody Document document) {
		return repository.insertBodyJson(document);
	}

	@PutMapping(value = "/update/query")
	public String updateByQuery(@RequestBody Map<String, String> map) {
		String query = map.get("query");
		return repository.updateByQuery(query);
	}
}