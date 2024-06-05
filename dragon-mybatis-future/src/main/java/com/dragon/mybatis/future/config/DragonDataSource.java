package com.dragon.mybatis.future.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import com.dragon.mybatis.future.entity.DataSourceInfo;
import com.dragon.mybatis.future.entity.DataSourceProperties;
import com.dragon.mybatis.future.utils.JdbcUtil;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class DragonDataSource extends AbstractRoutingDataSource {

	@Autowired
	private DataSourceProperties dataSourceProperties;

	@Override
	protected Object determineCurrentLookupKey() {
		return "IM_TIMESTAMP";
	}

	@Override
	public void afterPropertiesSet() {
		System.out.println("DragonDataSource afterPropertiesSet...");
		Map<Object, Object> targetDataSources = createDataSources();
		super.setTargetDataSources(targetDataSources);
		super.afterPropertiesSet();
	}

	private Map<Object, Object> createDataSources() {
		Map<Object, Object> dataSources = new HashMap<>();
		List<DataSourceInfo> datasourceInfos = dataSourceProperties.getDatasourceInfo();
		if (datasourceInfos != null && datasourceInfos.size() > 0) {
			for (DataSourceInfo dataSourceInfo : datasourceInfos) {
				HikariDataSource dataSource = new HikariDataSource();
				dataSource.setDriverClassName(JdbcUtil.driverClass(dataSourceInfo.getDatabaseId()));
				dataSource.setJdbcUrl(JdbcUtil.jdbcUrl(dataSourceInfo));
				dataSource.setUsername(dataSourceInfo.getUsername());
				dataSource.setPassword(dataSourceInfo.getPassword());
				dataSource.setPoolName("Dragon-Hikari-DataSource");
				// minIdle
				dataSource.setMinimumIdle(2);
				// maxActive
				dataSource.setMaximumPoolSize(4);
				// 连接建立超时时间
				dataSource.setConnectionTimeout(5000);
				// 空闲连接超时时间 60s
				dataSource.setIdleTimeout(60000);
				// 连接测试时间
				dataSource.setValidationTimeout(3000);
//				dataSource.getConnection();
				// 其他的参数可以默认加上去
				dataSources.put(dataSourceInfo.getDatasourceId(), dataSource);
			}
		}
		return dataSources;
	}

}
