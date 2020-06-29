package tw.com.aitc.SBE.CQRS.exception;

import java.util.UUID;

public class InsufficientBalanceException extends Throwable {
	public InsufficientBalanceException(UUID accountId, long amount) {
		super("Insufficient Balance: Cannot withdraw " + amount +
				" from account number [" + accountId.toString() + "]");
	}
}