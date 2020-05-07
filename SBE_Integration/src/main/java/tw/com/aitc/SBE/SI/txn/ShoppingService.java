package tw.com.aitc.SBE.SI.txn;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import tw.com.aitc.SBE.SI.order.Order;

@MessagingGateway(name = "ShoppingService")
public interface ShoppingService {

	@Gateway(requestChannel = "shopping_inbound")
	Order shopping(Order order);
}
