package com.dragon.kafka.future.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage() {
		System.out.println(kafkaTemplate.getProducerFactory().getConfigurationProperties());
		kafkaTemplate.send("topic_chen", "1");
	}

}
