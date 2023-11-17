package com.dragon.kafka.future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dragon.kafka.future.producer.SpringProducer;

@SpringBootTest
class KafkaFutureApplicationTests {

	@Autowired
	private SpringProducer producer;
	
	@Test
	void contextLoads() {
		producer.sendMessage();
	}

}
