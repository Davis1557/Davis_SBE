package tw.com.aitc.SBE;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.function.Function;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;

@Controller
class AuthController {

	@Value("${messages.base-uri}")
	private String messagesBaseUri;

	@Autowired
	private WebClient webClient;

	private Map retrieveMessages(String clientRegistrationId) {
		return this.webClient
				.get()
				.uri(this.messagesBaseUri)
				.attributes(clientRegistrationId(clientRegistrationId))
				.retrieve()
				.bodyToMono(Map.class)
				.filterWhen(new Function<Map, Publisher<Boolean>>() {
					@Override
					public Publisher<Boolean> apply(Map map) {
						return null;
					}
				})
				.block();
	}

	@GetMapping(value = "/")
	public String root(Model model) {
		return "redirect:/index";
	}

	@GetMapping(value = "/index")
	public String index(Model model) {
		return "index";
	}

	@GetMapping(value = "/authorize", params = "grant_type=authorization_code")
	public String authorize(Model model) {
		Map coupon = retrieveMessages("messaging-client-auth-code");
		model.addAttribute("coupon", coupon);
		return "index";
	}
}