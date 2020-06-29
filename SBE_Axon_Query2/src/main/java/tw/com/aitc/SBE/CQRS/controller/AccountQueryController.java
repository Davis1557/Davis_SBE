package tw.com.aitc.SBE.CQRS.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.aitc.SBE.CQRS.repository.entity.BankClient;
import tw.com.aitc.SBE.CQRS.service.AccountQueryService;

import java.util.concurrent.CompletableFuture;

@Slf4j              // Lombok
@RestController
@RequestMapping(value = "/clients")
@AllArgsConstructor
public class AccountQueryController {

	private final AccountQueryService accountQueryService;

	@GetMapping("/{name}")
	public CompletableFuture<BankClient> findByOwner(@PathVariable("name") String name) {
		log.info("Receive: /clients/{}", name);
		return this.accountQueryService.findById(name);
	}
}