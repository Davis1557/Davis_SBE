package tw.com.aitc.SBE.mongoDB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Profile("multiDB")
public class MongoConfiguration {

	@Bean
	public MongoClient mongoClient() {
		return MongoClients.create("mongodb://localhost:27017");
	}

	// MongoRepository 只認 name = mongoTemplate 的 Bean
	@Bean
	public MongoTemplate mongoTemplate(MongoClient client) {
		return new MongoTemplate(client, "SBE");
	}

	@Bean(name = "SBE2")
	@Primary
	public MongoTemplate mongoTemplateSBE2(MongoClient client) {
		return new MongoTemplate(client, "SBE2");
	}

}
