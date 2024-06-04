package com.dragon.event.future.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.dragon.event.future.event.MessageEvent;

@Component
public class MessageListener {

	@EventListener
	public void messageListener(MessageEvent messageEvent) {
		System.out.println(messageEvent);
	}

}
