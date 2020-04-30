package tw.com.aitc.SBE.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends MongoRepository<Customer, String> {

//	Customer findByName(String name);
}