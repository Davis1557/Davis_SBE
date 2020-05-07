package tw.com.aitc.SBE.SI.product;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Map;

@MessagingGateway(name = "ProductService")
public interface ProductService {

	@Gateway(replyChannel = "ship_inbound")
	Product ship(Product order);
}
