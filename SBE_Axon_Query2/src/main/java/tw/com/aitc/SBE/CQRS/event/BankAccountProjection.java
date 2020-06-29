package tw.com.aitc.SBE.CQRS.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import tw.com.aitc.SBE.CQRS.repository.BankClientRepository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankClient;
import tw.com.aitc.SBE.CQRS.service.query.FindBankClientQuery;

import java.util.Arrays;

@Slf4j                      // Lombok
@RequiredArgsConstructor    // Lombok
@Component
public class BankAccountProjection {

	private final BankClientRepository repository;

	@EventHandler
	public void on(AccountCreatedEvent event) {
		log.info("EventHandler >> AccountCreatedEvent: {}", event.getId());

		BankClient bankClient = repository.findById(event.getOwner()).orElse(null);

		if (bankClient == null) {
			repository.save(
					new BankClient(event.getOwner(), Arrays.asList(event.getId()))
			);
		}
		else {
			bankClient.getAccounts().add(event.getId());
			repository.save(bankClient);
		}
	}

	@QueryHandler
	public BankClient handle(FindBankClientQuery query) {
		log.info("QueryHandler >> FindBankClientQuery: {}", query);
		return this.repository.findById(query.getName()).orElse(null);
	}
}