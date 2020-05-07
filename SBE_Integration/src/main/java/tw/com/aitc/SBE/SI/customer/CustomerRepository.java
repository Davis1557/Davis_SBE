package tw.com.aitc.SBE.SI.customer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CustomerRepository extends MongoRepository<Customer, String> {
}