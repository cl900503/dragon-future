package com.dragon.mybatis.future;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.fastjson2.JSONObject;
import com.dragon.mybatis.future.config.DragonDataSource;
import com.dragon.mybatis.future.entity.DataSourceProperties;
import com.dragon.mybatis.future.utils.DataSourceUtil;

@SpringBootTest
class MybatisFutureApplicationTests {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(MybatisFutureApplicationTests.class);

	@Autowired
	private DragonDataSource dataSource;

	@Autowired
	private DataSourceProperties dataSourceProperties;
	
	@Autowired
	private DataSourceUtil dataSourceUtil;

	@Test
	void Test() throws SQLException {

//		DataSource dataSource = new DragonDataSource();
//		DataSource dataSource = DataSourceConfig.createDataSource();

		for (int i = 0; i < 10; i++) {
			Connection connection = dataSource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT version()");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
//				logger.debug(resultSet.getString(1));
			}
			resultSet.close();
			connection.close();
		}
		
		
		
//		String datasourceConfigStr = "{\"datasourceInfo\":[{\"datasourceId\":\"IM_TIMESTAMP\",\"databaseName\":\"IM_TIMESTAMP\",\"databaseId\":\"mysql\",\"ip\":\"192.168.0.85\",\"port\":11306,\"username\":\"root\",\"password\":\"R9UDvSJyO0fKi1bj\"}],\"codecEnabled\":true,\"tableInfo\":[{\"tableName\":\"IM_ID_SLOT\",\"tableShardType\":\"%1\",\"tableSeg\":[{\"tableSegName\":null,\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_IDTYPE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_PAGE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_PAGE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]}]}";
//		DataSourceProperties new_dataSourceProperties = dataSourceProperties = JSONObject.parseObject(datasourceConfigStr, DataSourceProperties.class);
//
//		dataSourceProperties.setDatasourceInfo(new_dataSourceProperties.getDatasourceInfo());
//		dataSourceProperties.setTableInfo(new_dataSourceProperties.getTableInfo());
//		dataSourceProperties.setCodecEnabled(new_dataSourceProperties.isCodecEnabled());
//		
//		System.out.println("新："+dataSourceProperties);
//		dataSource.afterPropertiesSet();
		
		for (int i = 0; i < 10; i++) {
			Connection connection = dataSource.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement("SELECT version()");
			ResultSet resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));
//				logger.debug(resultSet.getString(1));
			}
			resultSet.close();
			connection.close();
		}
	}

}
