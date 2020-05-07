package tw.com.aitc.SBE.SI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.http.dsl.Http;
import org.springframework.integration.support.MessageBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Configuration
@EnableIntegration
public class HttpSIConfiguration {

	@Bean
	public IntegrationFlow httpInbound() {
		return IntegrationFlows
				.from(Http.inboundGateway("/hello")
						.requestMapping(r -> r
								.methods(HttpMethod.POST)
								.consumes(MediaType.APPLICATION_JSON_VALUE)
								.produces(MediaType.APPLICATION_JSON_VALUE)
						)
						.requestPayloadType(Map.class)
						.mappedResponseHeaders("twice")
				)
				.handle(Map.class, (payload, headers) -> {
					String name = (String) payload.getOrDefault("name", "");
					payload.put("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
					return MessageBuilder.withPayload(payload).setHeader("twice", name + name).build();
				})

				.get();
	}
}
