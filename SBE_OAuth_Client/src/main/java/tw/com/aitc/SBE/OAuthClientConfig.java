package tw.com.aitc.SBE;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Configuration
public class OAuthClientConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuthClientConfig.class);

	@Bean
	WebClient webClient(OAuth2AuthorizedClientManager authorizedClientManager) {
		ServletOAuth2AuthorizedClientExchangeFilterFunction oauth2 = new ServletOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
		return WebClient.builder()
				.filter(ExchangeFilterFunction.ofRequestProcessor(req -> {
					StringBuilder sb = new StringBuilder();
					sb
							.append("\n==================================================")
							.append("\n\nSend Request : ")
							.append(req.method())
							.append(" ")
							.append(req.url())
							.append("\n\n==================================================");
					LOGGER.info(sb.toString());
					return Mono.just(req);
				}))
				.filter(ExchangeFilterFunction.ofResponseProcessor(resp -> {
					StringBuilder sb = new StringBuilder();
					sb
							.append("\n==================================================")
							.append("\n\nReceive Response")
							.append("\n\n==================================================");
					LOGGER.info(sb.toString());
					return Mono.just(resp);
				}))
				.apply(oauth2.oauth2Configuration())
				.build();
	}

	@Bean
	OAuth2AuthorizedClientManager authorizedClientManager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository authorizedClientRepository) {
		// Provider 用來提供授權的方式
		OAuth2AuthorizedClientProvider authorizedClientProvider =
				OAuth2AuthorizedClientProviderBuilder.builder()
						.authorizationCode()    // 能使用"授權碼"的方式 ( Client 重導頁面到 Auth Server 讓 User 自己登入 )
//						.refreshToken()         // 能刷新 Token
//						.clientCredentials()    // Client 直接幫 User 登入 Auth Server
//						.password()             // User 透過 Client 登入 Auth Server
						.build();

		// 建立一個 OAuth2AuthorizedClientManager，並將 Provider 設定進去
		DefaultOAuth2AuthorizedClientManager authorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, authorizedClientRepository);
		authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);

		return authorizedClientManager;
	}
}
