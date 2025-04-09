package com.dragon.websocket.future.client;

import java.net.URI;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class SpringWebSocketClient {
	public static void main(String[] args) {
		StandardWebSocketClient client = new StandardWebSocketClient();

		try {
			WebSocketSession session = client.doHandshake(new AbstractWebSocketHandler() {
				@Override
				public void afterConnectionEstablished(WebSocketSession session) throws Exception {
					System.out.println("Connected to server");
					session.sendMessage(new TextMessage("Hello, Server!"));
				}

				@Override
				protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
					System.out.println("Received: " + message.getPayload());
				}
			}, null, URI.create("ws://localhost:8080/websocket")).get();

			
			session.sendMessage(new TextMessage("xxxxx"));
			
			// 保持连接
			Thread.sleep(5000);
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
