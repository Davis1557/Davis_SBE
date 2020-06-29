package tw.com.aitc.SBE.CQRS.event.projection;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ReplayStatus;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import tw.com.aitc.SBE.CQRS.event.AccountCreatedEvent;
import tw.com.aitc.SBE.CQRS.event.MoneyDepositedEvent;
import tw.com.aitc.SBE.CQRS.event.MoneyWithdrewEvent;
import tw.com.aitc.SBE.CQRS.exception.AccountNotFoundException;
import tw.com.aitc.SBE.CQRS.service.query.FindBankAccountQuery;
import tw.com.aitc.SBE.CQRS.repository.BankAccountRepository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;

import java.util.Optional;

@Slf4j                      // Lombok
@RequiredArgsConstructor    // Lombok
@Component
public class BankAccountProjection {

	private final BankAccountRepository repository;

	@EventHandler
	public void on(AccountCreatedEvent event) {
		log.info("EventHandler >> AccountCreatedEvent: {}", event.getId());
		BankAccount bankAccount = new BankAccount(
				event.getId(),
				event.getOwner(),
				event.getInitialBalance(),
				event.getCreateDT(),
				event.getModifyDT()
		);
		this.repository.save(bankAccount);
	}

	@EventHandler
	public void on(MoneyDepositedEvent event) throws AccountNotFoundException {
		log.info("EventHandler >> MoneyDepositedEvent: {}", event.getId());
		Optional<BankAccount> optBankAccount = this.repository.findById(event.getId());
		BankAccount bankAccount = optBankAccount.orElseThrow(() -> new AccountNotFoundException(event.getId()));
		bankAccount.setBalance(bankAccount.getBalance() + event.getAmount());
		bankAccount.setModifyDT(event.getModifyDT());
		this.repository.save(bankAccount);
	}

	@EventHandler
	public void on(MoneyWithdrewEvent event) throws AccountNotFoundException {
		log.info("EventHandler >> MoneyWithdrewEvent: {}", event.getId());
		Optional<BankAccount> optBankAccount = this.repository.findById(event.getId());
		BankAccount bankAccount = optBankAccount.orElseThrow(() -> new AccountNotFoundException(event.getId()));
		bankAccount.setBalance(bankAccount.getBalance() - event.getAmount());
		bankAccount.setModifyDT(event.getModifyDT());
		this.repository.save(bankAccount);
	}

	@QueryHandler
	public BankAccount handle(FindBankAccountQuery query) {
		log.info("QueryHandler >> FindBankAccountQuery: {}", query);
		return this.repository.findById(query.getAccountId()).orElse(null);
	}
}