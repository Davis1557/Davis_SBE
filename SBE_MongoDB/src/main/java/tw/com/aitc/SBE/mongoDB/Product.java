package tw.com.aitc.SBE.mongoDB;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Product {

	public Product() {
	}

	public Product(String name) {
		this.name = name;
	}

	@Id
	public String id;

	public String name;

	@Override
	public String toString() {
		return "Product{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}
