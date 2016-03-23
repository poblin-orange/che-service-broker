package com.orange.chebroker.config;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.spring.client.SpringCloudFoundryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CfClientConfig {

	
	@Bean
	CloudFoundryClient cloudFoundryClient(@Value("${cf.host}") String host,
	                                      @Value("${cf.username}") String username,
	                                      @Value("${cf.password}") String password) {
	    return SpringCloudFoundryClient.builder()
	            .host(host)
	            .username(username)
	            .password(password)
	            .build();
	}
}
