package tw.com.aitc.SBE;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ResourceController {

	@GetMapping(value = "/coupon")
	public ResponseEntity<?> coupon() {
		Map<String, String> map = new HashMap<>();
		map.put("一號餐", "A11111");
		map.put("二號餐", "B22222");
		map.put("三號餐", "C33333");
		return ResponseEntity.ok(map);
	}
}