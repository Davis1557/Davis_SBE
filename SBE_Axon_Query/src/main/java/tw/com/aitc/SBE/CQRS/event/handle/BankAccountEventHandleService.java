package tw.com.aitc.SBE.CQRS.event.handle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import tw.com.aitc.SBE.CQRS.event.AccountCreatedEvent;
import tw.com.aitc.SBE.CQRS.repository.BankAccountRepository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;

@Slf4j                      // Lombok
@RequiredArgsConstructor    // Lombok
@ProcessingGroup("AMQPEvents")
@Service
public class BankAccountEventHandleService {

	private final BankAccountRepository repository;

	@EventHandler
	public void on(AccountCreatedEvent event) {
		log.info("EventHandler >> AccountCreatedEvent: {}", event.getId());
		BankAccount bankAccount = new BankAccount(
				event.getId(),
				event.getOwner()
		);
		this.repository.save(bankAccount);
	}
}