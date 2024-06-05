package com.dragon.mybatis.future.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DragonDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		return "aaa";
	}

	@Override
	public void afterPropertiesSet() {
		Map<Object, Object> targetDataSources = new HashMap<>();
		DataSource dataSource = DataSourceConfig.createDataSource();
		targetDataSources.put("aaa", dataSource);
		super.setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

}
