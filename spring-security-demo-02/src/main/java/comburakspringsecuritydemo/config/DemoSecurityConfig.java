package com.burak.springsecurity.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// add a reference to our security data source
	@Autowired
	private DataSource securityDataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// use jdbc authentication
		// tell spring security to use Jdbc authentication with our data source
		auth.jdbcAuthentication().dataSource(securityDataSource);
	}

	@Override
	// configure security of web paths in application,login,logout..
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()							// Restrict access based on roles
			.antMatchers("/").hasRole("EMPLOYEE")			// Path - Role , anyone who has employee role can access the home page 
			.antMatchers("/leaders/**").hasRole("MANAGER")	// Only managers can access leaders path and its subdirectories
			.antMatchers("/systems/**").hasRole("ADMIN")	// Only admins can access systems path and its subdirectories
			.and()
			.formLogin()									// customizing the form login process
			.loginPage("/showMyLoginPage")					// show the custom form at the request mapping
			.loginProcessingUrl("/authenticateTheUser")		// login form should POST data to this URL for processing (check user id pass)
			.permitAll()									// allow everyone to see login page, no need to be logged in.
			.and()
			.logout().permitAll()							// adds logout support
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
}
