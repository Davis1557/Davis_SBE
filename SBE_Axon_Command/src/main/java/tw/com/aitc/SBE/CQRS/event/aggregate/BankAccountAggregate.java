package tw.com.aitc.SBE.CQRS.event.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import tw.com.aitc.SBE.CQRS.service.command.CreateAccountCommand;
import tw.com.aitc.SBE.CQRS.service.command.DepositMoneyCommand;
import tw.com.aitc.SBE.CQRS.service.command.WithdrawMoneyCommand;
import tw.com.aitc.SBE.CQRS.event.AccountCreatedEvent;
import tw.com.aitc.SBE.CQRS.event.MoneyDepositedEvent;
import tw.com.aitc.SBE.CQRS.event.MoneyWithdrewEvent;
import tw.com.aitc.SBE.CQRS.exception.InsufficientBalanceException;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j              // Lombok
@AllArgsConstructor // Lombok
@NoArgsConstructor  // Lombok
@Getter             // Lombok
@Aggregate          // Axon
public class BankAccountAggregate {

	@AggregateIdentifier
	private UUID id;
	private String owner;
	private long balance;
	private LocalDateTime createDT;
	private LocalDateTime modifyDT;

	@CommandHandler
	public BankAccountAggregate(CreateAccountCommand command) {
		log.info("CommandHandler >> new AccountCreatedEvent: {}", command.getAccountId());
		AggregateLifecycle.apply(
				new AccountCreatedEvent(
						command.getAccountId(),
						command.getOwner(),
						command.getInitialBalance(),
						command.getCreateDT(),
						command.getModifyDT())
		);
	}

	@EventSourcingHandler
	public void on(AccountCreatedEvent event) {
		log.info("EventSourcingHandler >> AccountCreatedEvent: {}", event.getId());
		this.id = event.getId();
		this.owner = event.getOwner();
		this.balance = event.getInitialBalance();
		this.createDT = event.getCreateDT();
		this.modifyDT = event.getModifyDT();
	}

	@CommandHandler
	public void handle(DepositMoneyCommand command) {
		log.info("CommandHandler >> new MoneyDepositedEvent: {}", command.getAccountId());
		AggregateLifecycle.apply(
				new MoneyDepositedEvent(
						command.getAccountId(),
						command.getAmount(),
						command.getModifyDT()
				)
		);
	}

	@EventSourcingHandler
	public void on(MoneyDepositedEvent event) {
		log.info("EventSourcingHandler >> MoneyDepositedEvent: {}", event.getId());
		this.balance = this.balance + event.getAmount();
		this.modifyDT = event.getModifyDT();
	}

	@CommandHandler
	public void handle(WithdrawMoneyCommand command) {
		log.info("CommandHandler >> new MoneyWithdrewEvent: {}", command.getAccountId());
		AggregateLifecycle.apply(
				new MoneyWithdrewEvent(
						command.getAccountId(),
						command.getAmount(),
						command.getModifyDT()
				)
		);
	}

	@EventSourcingHandler
	public void on(MoneyWithdrewEvent event) throws InsufficientBalanceException {
		log.info("EventSourcingHandler >> MoneyWithdrewEvent: {}", event.getId());
		long newBalance = this.balance - event.getAmount();
		if (newBalance < 0) {
			log.info("EventSourcingHandler >> throw InsufficientBalanceException");
			throw new InsufficientBalanceException(event.getId(), event.getAmount());
		}
		this.balance = newBalance;
		this.modifyDT = event.getModifyDT();
	}
}