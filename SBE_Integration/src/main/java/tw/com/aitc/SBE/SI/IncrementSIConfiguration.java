package tw.com.aitc.SBE.SI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

@Configuration
@EnableIntegration
public class IncrementSIConfiguration {

	@Bean
	public MessageSource<?> intMsgSrc() {
//		return new JdbcPollingChannelAdapter(dataSource, "SELECT * FROM SOME_TABLE");
		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
		source.setObject(new AtomicInteger());
		source.setMethodName("getAndIncrement");
		return source;
	}

	@Bean
	public IntegrationFlow intFlow() {
		return IntegrationFlows
				.from(intMsgSrc(), c -> c.poller(Pollers.fixedRate(1000)))
				.filter((Integer p) -> p % 2 == 0)
				.route((Function<Integer, Boolean>) p -> p % 4 == 0,
						m -> m
								.channelMapping(true, "typeA")
								.channelMapping(false, "typeB")
				)
				.get();
	}

	@Bean
	public IntegrationFlow typeAFlow() {
		return IntegrationFlows
				.from("typeA")
				.transform(p -> "A " + p)
				.log()
				.get();
	}

	@Bean
	public IntegrationFlow typeBFlow() {
		return IntegrationFlows
				.from("typeB")
				.handle(Integer.class, (payload, headers) -> "B " + payload)
				.log()
				.get();
	}

}