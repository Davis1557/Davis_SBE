package tw.com.aitc.SBE.WebThymeleaf.atmTxn;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/customer")
public class WebController {

	AtmTxnService service;

	@PostMapping("/deposit")
	public AtmTxn deposit(AtmTxn txn) {
		return service.deposit(txn);
	}

	@PostMapping("/withdraw")
	public AtmTxn withdraw(AtmTxn txn) {
		return service.withdraw(txn);
	}
}
