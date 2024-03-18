package com.dragon.mongo.future;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.dragon.mongo.future.domain.User;

@SpringBootTest
class MongoFutureApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void testFindId() {
		try {
			User findById = mongoTemplate.findById("65f41f4c0944b13108d73b07", User.class, "user");
			System.out.println(findById);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testFind() {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is("小明"));
		List<User> users = mongoTemplate.find(query, User.class, "user");
		System.out.println(users);
	}

	@Test
	void testfindMyFieldValues() {
		try {

			MatchOperation match = Aggregation.match(Criteria.where("userName").is("小明"));
			ProjectionOperation project = Aggregation.project("sex").andExclude("_id");

			Aggregation aggregation = Aggregation.newAggregation(match, project);

			// 执行聚合查询并获取结果
			AggregationResults<String> results = mongoTemplate.aggregate(aggregation, "user", String.class);

			// 从结果中提取值列表
			List<String> ids = results.getMappedResults();

			System.out.println(ids);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testInsert() {
		User user1 = new User(1001L, "小明", "男");
		User user2 = new User(1002L, "小红", "女");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);
		Collection<User> result = mongoTemplate.insertAll(users);
		System.out.println(result);
	}

}
