package tw.com.aitc.SBE.CQRS.service;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import tw.com.aitc.SBE.CQRS.service.command.CreateAccountCommand;
import tw.com.aitc.SBE.CQRS.service.command.DepositMoneyCommand;
import tw.com.aitc.SBE.CQRS.service.command.WithdrawMoneyCommand;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;
import tw.com.aitc.SBE.CQRS.controller.dto.AccountCreationDTO;
import tw.com.aitc.SBE.CQRS.controller.dto.AmountDTO;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor     // Lombok
public class AccountCommandService {

	private final CommandGateway commandGateway;

	public CompletableFuture<BankAccount> createAccount(AccountCreationDTO creationDTO) {
		LocalDateTime now = LocalDateTime.now();
		return this.commandGateway.send(new CreateAccountCommand(
				UUID.randomUUID(),
				creationDTO.getOwner(),
				0, now, now)
		);
	}

	public CompletableFuture<String> depositMoneyToAccount(String accountId,
	                                                       AmountDTO amountDTO) {
		LocalDateTime now = LocalDateTime.now();
		return this.commandGateway.send(new DepositMoneyCommand(
				ServiceUtils.formatUUID(accountId),
				amountDTO.getAmount(),
				now)
		);
	}

	public CompletableFuture<String> withdrawMoneyFromAccount(String accountId,
	                                                          AmountDTO amountDTO) {
		LocalDateTime now = LocalDateTime.now();
		return this.commandGateway.send(new WithdrawMoneyCommand(
				ServiceUtils.formatUUID(accountId),
				amountDTO.getAmount(),
				now)
		);
	}
}