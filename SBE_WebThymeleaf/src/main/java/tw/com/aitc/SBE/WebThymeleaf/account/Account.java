package tw.com.aitc.SBE.WebThymeleaf.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@Column(nullable = false)
	private String account;

	@Column(nullable = false)
	private long balance;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account1 = (Account) o;
		return balance == account1.balance &&
				account.equals(account1.account);
	}

	@Override
	public int hashCode() {
		return Objects.hash(account, balance);
	}
}
