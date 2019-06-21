package cn.cenjp.platform.rabbitmq;

import cn.cenjp.platform.bean.SpikeMessage;
import cn.cenjp.platform.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MQSender {

	private static Logger log = LoggerFactory.getLogger(MQSender.class);
	
	@Autowired
	AmqpTemplate amqpTemplate ;
	
	public void sendSpileMessage(SpikeMessage message) {
		String msg = RedisService.beanToString(message);
		log.info("send message:"+msg);
		amqpTemplate.convertAndSend(MQConfig.SPIKE_QUEUE, msg);
	}
}
