package com.dragon.event.future;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.dragon.event.future.publisher.MessagePublisher;

@SpringBootTest
class EventFutureApplicationTests {

	@Autowired
	private MessagePublisher messagePublisher;

	@Test
	void Test() {

		for (long i = 0; i < 100; i++) {
			messagePublisher.publishMessageEvent(i);
		}

	}

}
