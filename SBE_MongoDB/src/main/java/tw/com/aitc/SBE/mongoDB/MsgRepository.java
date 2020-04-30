package tw.com.aitc.SBE.mongoDB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class MsgRepository {

	@Autowired
	public MongoTemplate template;

	// 參考: https://docs.mongodb.com/manual/reference/operator/query/
	// Example: {"string":{$regex:"[X]+"}}
	public List<Map> findByQuery(String queryString) {
		BasicQuery query = new BasicQuery(queryString);
		return template.find(query, Map.class, "msg");
	}

	public String updateByQuery(String queryString) {
		BasicQuery query = new BasicQuery(queryString);

		Update update = new Update().set("updateTime", LocalDateTime.now().toString());

		UpdateResult result = template.updateMulti(query, update, "msg");

		ObjectNode node = new ObjectMapper().createObjectNode();
		node.put("ModifiedCount", String.valueOf(result.getModifiedCount()));

		return node.toString();
	}

	public String insertBodyString(String body) {
		Document document = new Document();
		document.append("string", body);
		return template.insert(document, "msg").toJson();
	}

	public String insertBodyJson(Document document) {
		return template.insert(document, "msg").toJson();
	}
}
