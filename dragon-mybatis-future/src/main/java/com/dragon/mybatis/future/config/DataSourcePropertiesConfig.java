package com.dragon.mybatis.future.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dragon.mybatis.future.entity.DataSourceInfo;
import com.dragon.mybatis.future.entity.DataSourceProperties;

@Configuration
public class DataSourcePropertiesConfig {

	@Bean
	public static DataSourceProperties dataSourceProperties() {
//		String datasourceConfigStr = "{\"datasourceInfo\":[{\"datasourceId\":\"192.168.0.81\",\"databaseName\":\"IM_TIMESTAMP\",\"databaseId\":\"mysql\",\"ip\":\"192.168.0.81\",\"port\":11306,\"username\":\"root\",\"password\":\"knilVrvim0228%)\"},{\"datasourceId\":\"192.168.0.85\",\"databaseName\":\"IM_TIMESTAMP\",\"databaseId\":\"mysql\",\"ip\":\"192.168.0.85\",\"port\":11306,\"username\":\"root\",\"password\":\"R9UDvSJyO0fKi1bj\"}],\"codecEnabled\":true,\"tableInfo\":[{\"tableName\":\"IM_ID_SLOT\",\"tableShardType\":\"%1\",\"tableSeg\":[{\"tableSegName\":null,\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_IDTYPE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_PAGE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_PAGE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]}]}";
//		DataSourceProperties datasourceConfig = JSONObject.parseObject(datasourceConfigStr, DataSourceProperties.class);
		
		DataSourceProperties datasourceConfig = new DataSourceProperties();
		
		List<DataSourceInfo> datasourceInfos = new ArrayList<>();
		DataSourceInfo dataSourceInfo = new DataSourceInfo();
		dataSourceInfo.setDatasourceId("");
		dataSourceInfo.setDatabaseName("");
		dataSourceInfo.setDatabaseId("");
		dataSourceInfo.setIp("192.168.81.121");
		dataSourceInfo.setPort(2003);
		dataSourceInfo.setUsername("sysdba");
		dataSourceInfo.setPassword("szoscar55");
		
		datasourceInfos.add(dataSourceInfo);
		datasourceConfig.setDatasourceInfo(datasourceInfos);
		System.out.println(datasourceConfig);
		return datasourceConfig;
	}

}
