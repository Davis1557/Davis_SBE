package tw.com.aitc.SBE.CQRS.event.handle;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingModule;
import org.axonframework.extensions.amqp.eventhandling.AMQPMessageConverter;
import org.axonframework.extensions.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j          // Lombok
@Configuration
public class AMQPEventHandleConfiguration {

	@Autowired
	void configure(EventProcessingModule epm, SpringAMQPMessageSource msgSrc) {
		epm.registerSubscribingEventProcessor("AMQPMsgSrc", c -> msgSrc);
		epm.assignProcessingGroup("AMQPEvents", "AMQPMsgSrc");
	}

	@Bean
	public SpringAMQPMessageSource QQExchangeMsgSrc(AMQPMessageConverter messageConverter) {
		return new SpringAMQPMessageSource(messageConverter) {
			@RabbitListener(queues = "QQ")
			@Override
			public void onMessage(Message message, Channel channel) {
				log.info("Receive from RabbitMQ {} {}", message, channel);
				super.onMessage(message, channel);
			}
		};
	}
}