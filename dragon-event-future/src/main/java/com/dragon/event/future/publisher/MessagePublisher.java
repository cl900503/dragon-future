package com.dragon.event.future.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.dragon.event.future.domain.Message;
import com.dragon.event.future.event.MessageEvent;

@Component
public class MessagePublisher {

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publishMessageEvent(Long id) {
		Message message = new Message();
		message.setMessageId(id);
		message.setContent("xxxx" + id);
		MessageEvent messageEvent = new MessageEvent(message);
		applicationEventPublisher.publishEvent(messageEvent);
	}

}
