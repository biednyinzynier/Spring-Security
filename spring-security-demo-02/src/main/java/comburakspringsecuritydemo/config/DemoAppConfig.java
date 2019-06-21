package com.burak.springsecurity.demo.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.burak.springsecurity.demo")
@PropertySource("classpath:persistence-mysql.properties")		// will read properties file (src/main/resources) is standart maven directory
public class DemoAppConfig {
	// set up variable to hold the properties
	@Autowired
	private Environment env;			// will hold data read from properties files, Environment is a special class from the spring framework

	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	// define a bean for view resolver
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
		return viewResolver;
	}
	
	// define a bean for our security datasource
	@Bean
	public DataSource securityDataSource() {
		// create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set the jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty("jdbc.driver"));
		} catch (PropertyVetoException ex) {
			throw new RuntimeException(ex);
		}
		
		// log the connection props
		logger.info(">>> jdbc.url=" + env.getProperty("jdbc.url"));
		logger.info(">>> jdbc.user=" + env.getProperty("jdbc.user"));
		
		// set database connection props
		securityDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
		securityDataSource.setUser(env.getProperty("jdbc.user"));
		securityDataSource.setPassword(env.getProperty("jdbc.password"));
		
		// set connection pool props
		securityDataSource.setInitialPoolSize(getIntProperty("connection.pool.initialPoolSize"));
		securityDataSource.setMinPoolSize(getIntProperty("connection.pool.minPoolSize"));
		securityDataSource.setMaxPoolSize(getIntProperty("connection.pool.maxPoolSize"));
		securityDataSource.setMaxIdleTime(getIntProperty("connection.pool.maxIdleTime"));
		
		return securityDataSource;
		
	}
	
	// helper method to read environment property and convert to int
	private int getIntProperty(String propName) {
		String propVal = env.getProperty(propName);
		
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
}
