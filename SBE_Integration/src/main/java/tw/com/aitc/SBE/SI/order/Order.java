package tw.com.aitc.SBE.SI.order;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import tw.com.aitc.SBE.SI.customer.Customer;
import tw.com.aitc.SBE.SI.product.Product;

@Document
public class Order {

	@Id
	private String id;

	private Customer customer;

	private Product product;

	public String getId() {
		return id;
	}

	public Order setId(String id) {
		this.id = id;
		return this;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Order setCustomer(Customer customer) {
		this.customer = customer;
		return this;
	}

	public Product getProduct() {
		return product;
	}

	public Order setProduct(Product product) {
		this.product = product;
		return this;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id='" + id + '\'' +
				", customer=" + customer +
				", product=" + product +
				'}';
	}
}