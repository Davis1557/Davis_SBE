package tw.com.aitc.SBE.CQRS.service;

import lombok.AllArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;
import tw.com.aitc.SBE.CQRS.service.query.FindBankAccountQuery;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@AllArgsConstructor // Lombok
@Service
public class AccountQueryService {
	private final QueryGateway queryGateway;
	private final EventStore eventStore;

	public CompletableFuture<BankAccount> findById(String accountId) {
		return this.queryGateway.query(
				new FindBankAccountQuery(ServiceUtils.formatUUID(accountId)),
				ResponseTypes.instanceOf(BankAccount.class)
		);
	}

	public List<Object> listEventsForAccount(String accountId) {
		return this.eventStore
				.readEvents(accountId)
				.asStream()
				.map(Message::getPayload)
				.collect(Collectors.toList());
	}
}