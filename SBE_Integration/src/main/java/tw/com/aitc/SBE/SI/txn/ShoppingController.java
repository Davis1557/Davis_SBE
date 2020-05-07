package tw.com.aitc.SBE.SI.txn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.aitc.SBE.SI.order.Order;

@RestController
@RequestMapping("/")
public class ShoppingController {

	@Autowired
	ShoppingService shoppingService;

	@PostMapping(value = "/shopping")
	public Order shopping(@RequestBody Order order) {
		return shoppingService.shopping(order);
	}
}
