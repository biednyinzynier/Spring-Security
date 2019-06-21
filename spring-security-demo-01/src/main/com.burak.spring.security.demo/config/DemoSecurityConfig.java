package com.burak.springsecurity.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class DemoSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.inMemoryAuthentication()
			.withUser("burak").password(passwordEncoder().encode("1234")).roles("EMPLOYEE")
			.and()
			.withUser("hakan").password(passwordEncoder().encode("1234")).roles("EMPLOYEE", "MANAGER")
			.and()
			.withUser("osman").password(passwordEncoder().encode("1234")).roles("EMPLOYEE", "ADMIN");
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
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
