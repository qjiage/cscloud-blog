package com.jiayin.test.db;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.jiayin.test.util.BaseUtil;

@Configuration
@ConfigurationProperties(prefix = "spring.druid.datasource")
public class MyPropertyLoader extends PropertyPlaceholderConfigurer {  
  
	@Autowired
	private Properties props;       // 存取properties配置文件key-value结果
	 
    @Override  
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)  
            throws BeansException {  
    	
        String password = props.getProperty("password");
        try {  
            password = BaseUtil.getFromBase64(password);  
            props.setProperty("password", password);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        //props.get(arg0)  
        super.processProperties(beanFactoryToProcess, props);  
    }  
  
    
  /*  public class PropertyConfigurer extends PropertyPlaceholderConfigurer {

      

        @Override
        protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
                                throws BeansException {
          
        }

        public String getProperty(String key){
            return this.props.getProperty(key);
        }

        public String getProperty(String key, String defaultValue) {
            return this.props.getProperty(key, defaultValue);
        }

        public Object setProperty(String key, String value) {
            return this.props.setProperty(key, value);
        }
    }*/
  
} 