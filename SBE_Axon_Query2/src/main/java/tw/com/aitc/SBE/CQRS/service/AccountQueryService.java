package tw.com.aitc.SBE.CQRS.service;

import lombok.AllArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import tw.com.aitc.SBE.CQRS.repository.BankClientRepository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankClient;
import tw.com.aitc.SBE.CQRS.service.query.FindBankClientQuery;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@AllArgsConstructor // Lombok
@Service
public class AccountQueryService {
	private final QueryGateway queryGateway;

	public CompletableFuture<BankClient> findById(String name) {
		return this.queryGateway.query(
				new FindBankClientQuery(name),
				ResponseTypes.instanceOf(BankClient.class)
		);
	}
}