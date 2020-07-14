package tw.com.aitc.SBE.mongoDB;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MsgService {

	@Autowired
	public MsgRepository repository;

	public List<Map> findByQuery(String queryString) {
		Timer.Sample sample = Timer.start();
		List<Map> result = repository.findByQuery(queryString);
		sample.stop(Metrics.timer("davis.test.service.find.query"));
		return result;
	}

	public String updateByQuery(String queryString) {
		return repository.updateByQuery(queryString);
	}

	public String insertBodyString(String body) {
//		Metrics.counter("davis.test.service.insert.string", "Word", body.substring(0, 1).toUpperCase()).increment();
		Metrics.counter("davis.test.service.insert.string").increment();
		return repository.insertBodyString(body);
	}

	public String insertBodyJson(Document document) {
		return repository.insertBodyJson(document);
	}
}
