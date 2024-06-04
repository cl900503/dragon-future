package com.dragon.mybatis.future.entity;

import java.util.List;

import lombok.Data;

@Data
public class DataSourceProperties {
	private List<DataSourceInfo> datasourceInfo;
	private List<TableInfo> tableInfo;
	private boolean codecEnabled;
}
