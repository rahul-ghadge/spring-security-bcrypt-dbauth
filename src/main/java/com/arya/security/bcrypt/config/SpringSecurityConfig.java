package com.arya.security.bcrypt.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    
	@Resource
	public Environment env;

	
	@Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/").permitAll()
            .anyRequest().authenticated();
        
//        http.httpBasic()
//        .and()
//        .authorizeRequests()
//		.antMatchers("/admin").hasAnyRole("ADMIN")
//		.antMatchers("/user").hasRole("ADMIN", "USER")
//		.antMatchers("/").permitAll().and().formLogin()
//		.and()
//		.logout().permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource())
            .authoritiesByUsernameQuery("select USERNAME, ROLE from EMPLOYEE_AUTH where USERNAME=?")
            .usersByUsernameQuery("select USERNAME, PASSWORD, 1 as enabled from EMPLOYEE_AUTH where USERNAME=?")
            ;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
    
    
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setConnectionProperties(getConnectioProperties());
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }


    private static Properties getConnectioProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("spring.jpa.properties.hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("spring.jpa.hibernate.ddl-auto", "update");
		
		return properties;
	}
}


