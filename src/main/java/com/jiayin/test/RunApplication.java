package com.jiayin.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jiayin.test.util.BaseUtil;

@SpringBootApplication
@MapperScan("com.jiayin.test.daomap")//将项目中对应的mapper类的路径加进来就可以了
public class RunApplication {

	public static void main(String[] args) throws IOException {
		InputStream input = RunApplication.class.getClassLoader().getResourceAsStream("db.properties");
		Properties pro = new Properties();
		pro.load(input);
		String pwd = pro.getProperty("spring.druid.datasource.password");
		pro.setProperty("spring.druid.datasource.password", BaseUtil.getFromBase64(pwd));
		SpringApplication app= new SpringApplication(RunApplication.class);
		app.setDefaultProperties(pro);
		app.run(args);
	}
}
