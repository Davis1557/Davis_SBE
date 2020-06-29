package tw.com.aitc.SBE.CQRS.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.aitc.SBE.CQRS.service.AccountQueryService;

import java.util.List;
import java.util.UUID;

@Slf4j              // Lombok
@RestController
@RequestMapping(value = "/accounts")
@AllArgsConstructor
public class AccountQueryController {

	private final AccountQueryService accountQueryService;

	@GetMapping("/{owner}")
	public List<UUID> findByOwner(@PathVariable("owner") String owner) {
		log.info("Receive: /accounts/{}", owner);
		return this.accountQueryService.findByOwner(owner);
	}
}