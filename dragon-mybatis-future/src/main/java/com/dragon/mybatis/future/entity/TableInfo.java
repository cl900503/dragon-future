package com.dragon.mybatis.future.entity;

import java.util.List;

import lombok.Data;

@Data
public class TableInfo {
	private String tableName;
	private String tableShardType;
	private List<TableSeg> tableSeg;
}
