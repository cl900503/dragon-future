package com.dragon.mybatis.future.datasource;

import javax.sql.DataSource;

import com.dragon.mybatis.future.entity.DataSourceInfo;
import com.dragon.mybatis.future.entity.DataSourceProperties;
import com.dragon.mybatis.future.tools.DataSourceTools;
import com.dragon.mybatis.future.tools.JdbcUtil;
import com.zaxxer.hikari.HikariDataSource;

//@Component
public class DataSourceConfig {

//	@Autowired
//	private DataSourceProperties dataSourceProperties;

	public static DataSource createDataSource() {
		DataSourceProperties dataSourceProperties = DataSourceTools.getDataSourceProperties();
		DataSourceInfo dataSourceInfo = dataSourceProperties.getDatasourceInfo().get(0);
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
//		dataSource.getConnection();
		// 其他的参数可以默认加上去
		return dataSource;

	}

}
