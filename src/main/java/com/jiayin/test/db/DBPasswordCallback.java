package com.jiayin.test.db;

import java.util.Properties;

import org.springframework.stereotype.Component;

import com.alibaba.druid.util.DruidPasswordCallback;
import com.jiayin.test.util.BaseUtil;

//@Component
public class DBPasswordCallback extends DruidPasswordCallback {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void setProperties(Properties props) {

		String password = props.getProperty("password");
		try {
			password = BaseUtil.getFromBase64(password);
			setPassword(password.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// props.get(arg0)
		super.setProperties(props);
	}

	/*
	 * @Override protected void processProperties(ConfigurableListableBeanFactory
	 * beanFactoryToProcess, Properties props) throws BeansException {
	 * 
	 * String password = props.getProperty("password"); try { password =
	 * BaseUtil.getFromBase64(password); props.setProperty("password", password); }
	 * catch (Exception e) { e.printStackTrace(); } //props.get(arg0)
	 * super.processProperties(beanFactoryToProcess, props); }
	 */

	/*
	 * public class PropertyConfigurer extends PropertyPlaceholderConfigurer {
	 * 
	 * 
	 * 
	 * @Override protected void processProperties(ConfigurableListableBeanFactory
	 * beanFactoryToProcess, Properties props) throws BeansException {
	 * 
	 * }
	 * 
	 * public String getProperty(String key){ return this.props.getProperty(key); }
	 * 
	 * public String getProperty(String key, String defaultValue) { return
	 * this.props.getProperty(key, defaultValue); }
	 * 
	 * public Object setProperty(String key, String value) { return
	 * this.props.setProperty(key, value); } }
	 */

}