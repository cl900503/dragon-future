package com.dragon.mongo.future;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
class MongoFutureApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void testFind() {
		try {
			Document findById = mongoTemplate.findById(123456, Document.class, "user");
			System.out.println(findById);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
