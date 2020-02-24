package tw.com.aitc.SBE.WebThymeleaf.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "Customer")
public class Customer {

	public static final Customer EMPTY = new Customer().setId("").setAccount("").setName("");

	@Id
	@Column(nullable = false)
	private String id;

	@Column(nullable = false, unique = true)
	private String account;

	@Column(nullable = false)
	private String name;

	public String getId() {
		return id;
	}

	public Customer setId(String id) {
		this.id = id;
		return this;
	}

	public String getAccount() {
		return account;
	}

	public Customer setAccount(String account) {
		this.account = account;
		return this;
	}

	public String getName() {
		return name;
	}

	public Customer setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id='" + id + '\'' +
				", account='" + account + '\'' +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Customer customer = (Customer) o;
		return id.equals(customer.id) &&
				account.equals(customer.account) &&
				name.equals(customer.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, account, name);
	}
}
