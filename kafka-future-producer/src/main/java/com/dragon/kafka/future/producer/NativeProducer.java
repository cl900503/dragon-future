package com.dragon.kafka.future.producer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class NativeProducer {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		Properties props = new Properties();
		// 使用ssl端口
		props.put("bootstrap.servers", "192.168.0.81:11090");
		// serializer
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		
		Producer<String, String> producer = new KafkaProducer<String, String>(props);
		Future<RecordMetadata> future = producer.send(new ProducerRecord<String, String>("topic_chen", "5"));
		System.out.println(future.get().offset());
		producer.close();

	}

}
