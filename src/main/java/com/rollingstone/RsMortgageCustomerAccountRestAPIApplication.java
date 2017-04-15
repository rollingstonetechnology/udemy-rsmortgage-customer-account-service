package com.rollingstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * This is the primary Spring Boot application class. It configures Spring Boot, JPA, Swagger and 
 * other dependent Spring modules.
 */ 

@SuppressWarnings("deprecation")
@EnableAutoConfiguration  // Sprint Boot Automatic Configuration
@ComponentScan(basePackages = "com.rollingstone")
@EnableJpaRepositories("com.rollingstone.dao.jpa") // To segregate MongoDB and JPA repositories. Otherwise not needed.
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
public class RsMortgageCustomerAccountRestAPIApplication implements ApplicationContextAware{

    private static final Class<RsMortgageCustomerAccountRestAPIApplication> applicationClass = RsMortgageCustomerAccountRestAPIApplication.class;
    private static final Logger log = LoggerFactory.getLogger(applicationClass);

	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	 @Override
	    public void setApplicationContext(ApplicationContext ac) throws BeansException {
	        //force the bean to get loaded as soon as possible
	        ac.getBean("requestMappingHandlerAdapter");
	    }

 /*   @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }*/

}
