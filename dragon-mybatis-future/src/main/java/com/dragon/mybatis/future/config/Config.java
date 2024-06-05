package com.dragon.mybatis.future.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.dragon.mybatis.future.datasource.DragonDataSource;

@Component
public class Config {

	@Bean
	DataSource DataSource() {
		return new DragonDataSource();
	}
	
}
