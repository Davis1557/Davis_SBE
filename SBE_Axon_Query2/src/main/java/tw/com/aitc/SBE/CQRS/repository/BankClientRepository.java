package tw.com.aitc.SBE.CQRS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankClient;

@Repository
public interface BankClientRepository extends MongoRepository<BankClient, String> {
}