package tw.com.aitc.SBE.CQRS.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import tw.com.aitc.SBE.CQRS.controller.dto.AccountCreationDTO;
import tw.com.aitc.SBE.CQRS.controller.dto.AmountDTO;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;
import tw.com.aitc.SBE.CQRS.service.AccountCommandService;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j              // Lombok
@RestController
@RequestMapping("/accounts")
@AllArgsConstructor
public class AccountCommandController {
	private final AccountCommandService accountCommandService;

	@PostMapping("/create")
	@ResponseStatus(value = CREATED)
	public CompletableFuture<BankAccount> createAccount(@RequestBody AccountCreationDTO creationDTO) {
		log.info("Receive: /accounts/create");
		return this.accountCommandService.createAccount(creationDTO);
	}

	@PutMapping(value = "/{accountId}/deposit")
	public CompletableFuture<String> creditMoneyToAccount(@PathVariable(value = "accountId") String accountId,
	                                                      @RequestBody AmountDTO moneyCreditDTO) {
		log.info("Receive: /accounts/{}/deposit", accountId);
		return this.accountCommandService.depositMoneyToAccount(accountId, moneyCreditDTO);
	}

	@PutMapping(value = "/{accountId}/withdraw")
	public CompletableFuture<String> withdrawMoneyFromAccount(@PathVariable(value = "accountId") String accountId,
	                                                          @RequestBody AmountDTO moneyDebitDTO) {
		log.info("Receive: /accounts/{}/withdraw", accountId);
		return this.accountCommandService.withdrawMoneyFromAccount(accountId, moneyDebitDTO);
	}
}