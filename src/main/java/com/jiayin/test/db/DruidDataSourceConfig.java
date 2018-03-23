package com.jiayin.test.db;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

@Configuration
@EnableConfigurationProperties(DruidSettings.class)
public class DruidDataSourceConfig {

	@Autowired
	private DruidSettings druidSettings;

	@Bean
	public DBPasswordCallback getDBPasswordCallback() {
		return new DBPasswordCallback();
	}

	// @Bean
	@Bean(name = "dataSource", initMethod = "init", destroyMethod = "close", autowire = Autowire.BY_TYPE)
	@ConfigurationProperties("spring.druid.datasource")
	public DruidDataSource dataSource() throws Exception {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(druidSettings.getDriverClassName());
		dataSource.setUrl(druidSettings.getUrl());
		dataSource.setUsername(druidSettings.getUsername());
		dataSource.setPassword(druidSettings.getPassword());
		dataSource.setInitialSize(druidSettings.getInitialSize());
		dataSource.setMinIdle(druidSettings.getMinIdle());
		dataSource.setMaxActive(druidSettings.getMaxActive());
		dataSource.setMaxWait(druidSettings.getMaxWait());
		dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
		dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
		String validationQuery = druidSettings.getValidationQuery();
		if (validationQuery != null && !"".equals(validationQuery)) {
			dataSource.setValidationQuery(validationQuery);
		}
		dataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
		dataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
		dataSource.setTestOnReturn(druidSettings.isTestOnReturn());
		if (druidSettings.isPoolPreparedStatements()) {
			dataSource.setMaxPoolPreparedStatementPerConnectionSize(
					druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
		}
		dataSource.setFilters(druidSettings.getFilters());// 这是最关键的,否则SQL监控无法生效
		String connectionPropertiesStr = druidSettings.getConnectionProperties();
		if (connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)) {
			Properties connectProperties = new Properties();
			String[] propertiesList = connectionPropertiesStr.split(";");
			for (String propertiesTmp : propertiesList) {
				String[] obj = propertiesTmp.split("=");
				String key = obj[0];
				String value = obj[1];
				connectProperties.put(key, value);
			}
			dataSource.setConnectProperties(connectProperties);
		}
		dataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());

		return dataSource;
	}

	@Bean
	public ServletRegistrationBean druidStatViewServlet() {
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		registrationBean.addInitParameter("allow", "127.0.0.1");// IP白名单 (没有配置或者为空，则允许所有访问)
		registrationBean.addInitParameter("deny", "");// IP黑名单 (存在共同时，deny优先于allow)
		registrationBean.addInitParameter("loginUsername", "root");
		registrationBean.addInitParameter("loginPassword", "wtadmin");
		registrationBean.addInitParameter("resetEnable", "false");
		return registrationBean;
	}

	@Bean
	public FilterRegistrationBean druidWebStatViewFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
		registrationBean.addInitParameter("urlPatterns", "/*");
		registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
		return registrationBean;
	}

}