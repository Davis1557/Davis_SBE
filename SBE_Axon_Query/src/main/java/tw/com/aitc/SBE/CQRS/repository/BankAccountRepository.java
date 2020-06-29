package tw.com.aitc.SBE.CQRS.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;

import java.util.List;
import java.util.UUID;

@Repository
public interface BankAccountRepository extends MongoRepository<BankAccount, UUID> {
	List<BankAccount> findByOwner(String Owner);
}