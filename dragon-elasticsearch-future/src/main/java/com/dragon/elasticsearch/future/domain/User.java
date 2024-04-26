package com.dragon.elasticsearch.future.domain;

import java.util.Map;

import lombok.Data;

@Data
public class User {

	private Long userId;
	private String name;
	private Map<String, Byte> searchSetting;

}
