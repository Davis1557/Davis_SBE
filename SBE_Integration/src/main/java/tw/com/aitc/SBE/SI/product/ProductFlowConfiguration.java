package tw.com.aitc.SBE.SI.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import tw.com.aitc.SBE.SI.order.Order;

@Configuration
public class ProductFlowConfiguration {

	@Autowired
	ProductRepository productRepository;

	@Bean
	public IntegrationFlow ship() {
		return IntegrationFlows
				.from("ship_inbound")
				.handle(Order.class, (p, h) -> {
					Product orderProduct = p.getProduct();
					Product dbProduct = productRepository.findById(orderProduct.getId()).orElse(null);
					if (dbProduct == null) {
						throw new RuntimeException("Product is not found");
					}
					else if (dbProduct.getQuantity() < orderProduct.getQuantity()) {
						throw new RuntimeException("Product is not enough");
					}
					else {
						productRepository.save(dbProduct.setQuantity(dbProduct.getQuantity() - orderProduct.getQuantity()));
					}
					return p;
				})
				.get();
	}
}
