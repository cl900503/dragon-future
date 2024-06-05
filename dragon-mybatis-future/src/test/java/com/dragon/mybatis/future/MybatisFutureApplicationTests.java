package com.dragon.mybatis.future;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisFutureApplicationTests {
	/**
	 * 日志对象
	 */
	private static Logger logger = LoggerFactory.getLogger(MybatisFutureApplicationTests.class);

	@Autowired
	private DataSource dataSource;

	@Test
	void Test() throws SQLException {

//		DataSource dataSource = new DragonDataSource();
//		DataSource dataSource = DataSourceConfig.createDataSource();

		for (int i = 0; i < 100; i++) {
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
