package com.dragon.kafka.future.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class NativeConsumer {

	public static void main(String[] args) {

		Properties props = new Properties();
		// 服务地址端口
		props.put("bootstrap.servers", "192.168.0.81:11090");
		// deserializer
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		props.put("group.id", "NativeConsumer");

		Consumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		try {
			consumer.subscribe(Collections.singletonList("topic_chen"));

			int pauseTimes = 0;
			boolean pause = false;

			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> record : records) {
					System.out.println("消费端：" + record);
					if (!pause) {
						List<TopicPartition> list = new ArrayList<>();
						list.add(new TopicPartition(record.topic(), record.partition()));
						consumer.pause(list);
						pause = true;
						System.out.println("执行 pause:" + list);
//						System.out.println(record.topic());
//						System.out.println(record.partition());
					}
				}

				pauseTimes = pauseTimes + 1;
				System.out.println(pauseTimes + ":" + consumer.paused());
				if (pauseTimes == 50) {
					consumer.resume(consumer.paused());
				}

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			consumer.close();
		}

	}

}
