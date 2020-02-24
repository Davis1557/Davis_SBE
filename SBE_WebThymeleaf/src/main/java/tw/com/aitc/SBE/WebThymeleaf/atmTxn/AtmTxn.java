package tw.com.aitc.SBE.WebThymeleaf.atmTxn;

import java.util.Objects;

public class AtmTxn {

	private String id;

	private long amount;

	private String message;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AtmTxn atmTxn = (AtmTxn) o;
		return amount == atmTxn.amount &&
				id.equals(atmTxn.id) &&
				message.equals(atmTxn.message);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, amount, message);
	}
}
