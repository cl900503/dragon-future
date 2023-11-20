package com.dragon.kafka.future.consumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class NativeConsumer {

	public static void main(String[] args) {

		Properties props = new Properties();
		// 使用ssl端口
		props.put("bootstrap.servers", "192.168.0.81:11090");
		// deserializer
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("group.id", "NativeConsumer");

		Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		try {
			consumer.subscribe(Collections.singletonList("topic_chen"));
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("消费端：" + record);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			consumer.close();
		}

	}

}
