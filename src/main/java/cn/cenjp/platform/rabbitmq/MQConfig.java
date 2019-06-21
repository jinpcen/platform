package cn.cenjp.platform.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String SPIKE_QUEUE = "SPIKE.QUEUE";

	/**
	 * Direct模式 交换机Exchange
	 * */
	@Bean
	public Queue queue() {
		return new Queue(SPIKE_QUEUE, true);
	}
}
