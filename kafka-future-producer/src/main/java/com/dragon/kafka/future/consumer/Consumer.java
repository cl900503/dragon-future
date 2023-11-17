package com.dragon.kafka.future.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	
    @KafkaListener(topics = "topic_chen")
    public void processMessage(String content) {
        System.out.println(content);
    }

}
