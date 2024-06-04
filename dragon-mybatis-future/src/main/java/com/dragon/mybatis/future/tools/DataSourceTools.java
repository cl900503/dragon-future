package com.dragon.mybatis.future.tools;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSONObject;
import com.dragon.mybatis.future.entity.DataSourceProperties;

@Component
public class DataSourceTools {

	public DataSourceProperties getDataSourceConfig() {
		String datasourceConfigStr = "{\"datasourceInfo\":[{\"datasourceId\":\"IM_TIMESTAMP\",\"databaseName\":\"IM_TIMESTAMP\",\"databaseId\":\"mysql\",\"ip\":\"127.0.0.1\",\"port\":11306,\"username\":\"IWDlevyGj2TI0DT7gOjuTmvBkrVjoqoNQU1cvQ==\",\"password\":\"2v2mJN44QCTPlZNzyBo0dp/EDzLE+M4w/2OVuAnDXPLB/HGR8j8puSIrp9g=\"}],\"codecEnabled\":true,\"tableInfo\":[{\"tableName\":\"IM_ID_SLOT\",\"tableShardType\":\"%1\",\"tableSeg\":[{\"tableSegName\":null,\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_IDTYPE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_IDTYPE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]},{\"tableName\":\"IM_TIMESTAMP_PAGE\",\"tableShardType\":\"%5\",\"tableSeg\":[{\"tableSegName\":\"IM_TIMESTAMP_PAGE_0\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_1\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_2\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_3\",\"datasourceId\":\"IM_TIMESTAMP\"},{\"tableSegName\":\"IM_TIMESTAMP_PAGE_4\",\"datasourceId\":\"IM_TIMESTAMP\"}]}]}";
		DataSourceProperties datasourceConfig = JSONObject.parseObject(datasourceConfigStr, DataSourceProperties.class);
		System.out.println(datasourceConfig);
		return datasourceConfig;
	}

}
