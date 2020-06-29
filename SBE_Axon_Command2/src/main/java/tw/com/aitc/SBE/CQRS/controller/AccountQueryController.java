package tw.com.aitc.SBE.CQRS.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;
import tw.com.aitc.SBE.CQRS.service.AccountQueryService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j              // Lombok
@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountQueryController {

    private final AccountQueryService accountQueryService;

    @GetMapping("/{accountId}")
    public CompletableFuture<BankAccount> findById(@PathVariable("accountId") String accountId) {
        log.info("Receive: /accounts/{}", accountId);
        return this.accountQueryService.findById(accountId);
    }

    @GetMapping("/{accountId}/events")
    public List<Object> listEventsForAccount(@PathVariable(value = "accountId") String accountId) {
        log.info("Receive: /accounts/{}/events", accountId);
        return this.accountQueryService.listEventsForAccount(accountId);
    }
}