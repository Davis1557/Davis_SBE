package tw.com.aitc.SBE.SI.txn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import tw.com.aitc.SBE.SI.customer.CustomerRepository;
import tw.com.aitc.SBE.SI.order.Order;
import tw.com.aitc.SBE.SI.order.OrderRepository;

@Configuration
public class ShoppingFlowConfiguration {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;

	@Bean
	public IntegrationFlow shopping() {
		return IntegrationFlows
				.from("shopping_inbound")
				.handle(Order.class, (p, h) -> {
					if (!customerRepository.existsById(p.getCustomer().getId())) {
						throw new RuntimeException("Customer is not found");
					}
					return p;
				})
				.gateway("ship_inbound")
				.handle(Order.class, new GenericHandler<Order>() {
					@Override
					public Object handle(Order p, MessageHeaders h) {
						orderRepository.save(p);
						return p;
					}
				})
				.get();
	}
}
