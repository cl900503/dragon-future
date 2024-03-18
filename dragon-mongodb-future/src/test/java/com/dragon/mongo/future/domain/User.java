package com.dragon.mongo.future.domain;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class User {

	private ObjectId id;
	private long userId;
	private String userName;
	private String sex;

	public User(long userId, String userName, String sex) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.sex = sex;
	}

}
