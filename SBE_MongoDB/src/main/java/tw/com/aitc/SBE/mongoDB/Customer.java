package tw.com.aitc.SBE.mongoDB;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

	public Customer() {
	}

	public Customer(String name) {
		this.name = name;
	}

	@Id
	private String id;

	private String name;

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}