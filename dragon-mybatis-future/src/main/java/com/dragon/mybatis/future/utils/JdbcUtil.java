package com.dragon.mybatis.future.utils;

import com.dragon.mybatis.future.entity.DataSourceInfo;

/**
 * jdbc工具
 * 
 * @author chenlong
 * @date 2021-08-10 10:12:42
 */
public class JdbcUtil {

//	/**
//	 * 日志对象
//	 */
//	private static Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

	/**
	 * 根据databaseId获取driverClass
	 * 
	 * @author chenlong
	 * @date 2021-08-10 10:00:52
	 * @param databaseId
	 * @return
	 */
	public static String driverClass(String databaseId) {
		String driverClass = "";
		switch (databaseId) {
		// mysql
		case "mysql":
			driverClass = "com.mysql.cj.jdbc.Driver";
			break;
		// 金仓
		case "kingBase":
			driverClass = "com.kingbase8.Driver";
			break;
		// oracle
		case "oracle2":
			driverClass = "oracle.jdbc.driver.OracleDriver";
			break;
		// 昆仑
		case "kunlun":
			driverClass = "com.kunlun.jdbc.Driver";
			break;
		// 达梦
		case "dm":
			driverClass = "dm.jdbc.driver.DmDriver";
			break;
		// 神通
		case "oscar":
			driverClass = "com.oscar.Driver";
			break;
		case "gbase":
			driverClass = "com.gbasedbt.jdbc.Driver";
			break;
		case "vastbase":
			driverClass = "org.postgresql.Driver";
			break;
		default:
			break;
		}

		return driverClass;
	}

	/**
	 * 组装jdbcUrl
	 * 
	 * @author chenlong
	 * @date 2021-08-10 10:07:26
	 * @param dataSourceInfo
	 * @return
	 */
	public static String jdbcUrl(DataSourceInfo dataSourceInfo) {
		String jdbcUrl = "";
		switch (dataSourceInfo.getDatabaseId()) {
		// mysql
		case "mysql":
			jdbcUrl = "jdbc:mysql://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName()
					+ "?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
			break;
		// 金仓
		case "kingBase":
			jdbcUrl = "jdbc:kingbase8://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName();
			break;
		// oracle
		case "oracle2":
			jdbcUrl = "jdbc:oracle:thin:@//" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName();
			break;
		// 昆仑
		case "kunlun":
			jdbcUrl = "jdbc:kunlun://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName() + "?servers=" + dataSourceInfo.getIp();
			break;
		// 达梦
		case "dm":
			jdbcUrl = "jdbc:dm://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/";
			break;
		// 神通
		case "oscar":
			jdbcUrl = "jdbc:oscar://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName();
			break;
		case "gbase":
			jdbcUrl = "jdbc:gbasedbt-sqli://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName()
					+ ":GBASEDBTSERVER=gbase01;NEWCODESET=UTF8,zh_cn.UTF8,57372;DB_LOCALE=zh_cn.57372;SQLMODE=Oracle;delimident=y";
			break;
		case "vastbase":
			// jdbc:postgresql://host:port/database
			jdbcUrl = "jdbc:postgresql://" + dataSourceInfo.getIp() + ":" + dataSourceInfo.getPort() + "/"
					+ dataSourceInfo.getDatabaseName();
			break;

		default:
			break;
		}

		return jdbcUrl;
	}

}
