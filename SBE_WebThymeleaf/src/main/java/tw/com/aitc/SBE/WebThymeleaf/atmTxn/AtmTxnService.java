package tw.com.aitc.SBE.WebThymeleaf.atmTxn;

import tw.com.aitc.SBE.WebThymeleaf.account.Account;
import tw.com.aitc.SBE.WebThymeleaf.account.AccountRepository;
import tw.com.aitc.SBE.WebThymeleaf.customer.Customer;
import tw.com.aitc.SBE.WebThymeleaf.customer.CustomerRepository;

import java.util.Optional;

public class AtmTxnService {

	CustomerRepository cusRepo;
	AccountRepository accRepo;

	private Account toGetAccount(AtmTxn atmTxn) throws Exception {
		String id = Optional.of(atmTxn.getId())
				.orElseThrow(() -> new Exception("Id Input Not Found"));
		Long amount = Optional.of(atmTxn.getAmount())
				.orElseThrow(() -> new Exception("Amount Input Not Found"));
		if (amount <= 0) {
			throw new Exception("Invalid Amount");
		}
		Customer cusData = cusRepo.findById(id)
				.orElseThrow(() -> new Exception("Customer Data Not Found"));
		String account = Optional.of(cusData.getAccount())
				.orElseThrow(() -> new Exception("Account in Customer Not Found"));
		return accRepo.findById(account)
				.orElseThrow(() -> new Exception("Account Data Not Found"));
	}

	public AtmTxn deposit(AtmTxn atmTxn) {
		try {
			Account accData = toGetAccount(atmTxn);
			long newBalance = accData.getBalance() + atmTxn.getAmount();
			accData.setBalance(newBalance);
			accRepo.save(accData);
			atmTxn.setMessage("new Balance = " + newBalance);
		}
		catch (Exception e) {
			atmTxn.setMessage(e.getMessage());
		}
		return atmTxn;
	}

	public AtmTxn withdraw(AtmTxn atmTxn) {
		try {
			Account accData = toGetAccount(atmTxn);
			long newBalance = accData.getBalance() - atmTxn.getAmount();
			if (newBalance > 0) {
				accData.setBalance(newBalance);
				accRepo.save(accData);
				atmTxn.setMessage("new Balance = " + newBalance);
			}
			else {
				throw new Exception("Insufficient Balance");
			}
		}
		catch (Exception e) {
			atmTxn.setMessage(e.getMessage());
		}
		return atmTxn;
	}
}
