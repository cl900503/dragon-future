package com.dragon.kafka.future.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class SpringConsumer {
	
    @KafkaListener(topics = "topic_chen")
    public void processMessage(ConsumerRecord<String, String> consumerRecord) {
        System.out.println(consumerRecord);
    }

}
