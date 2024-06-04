package com.dragon.event.future.event;

import org.springframework.context.ApplicationEvent;

import com.dragon.event.future.domain.Message;

import lombok.Data;

@Data
public class MessageEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private Message message;

	public MessageEvent(Message message) {
		super(message);
		this.message = message;
	}

}
