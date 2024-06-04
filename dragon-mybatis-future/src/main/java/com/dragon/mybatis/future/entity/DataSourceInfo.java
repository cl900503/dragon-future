package com.dragon.mybatis.future.entity;

import lombok.Data;

@Data
public class DataSourceInfo {
	private String datasourceId;
	private String databaseName;
	private String databaseId;
	private String ip;
	private int port;
	private String username;
	private String password;
}
