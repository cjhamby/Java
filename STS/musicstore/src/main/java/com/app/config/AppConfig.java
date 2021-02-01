package com.app.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.repository.init.Jackson2RepositoryPopulatorFactoryBean;

@Configuration
public class AppConfig {
	
	
	/*
	 * hey look!
	 * a way to populate repositories ahead of time
	 * it's totally straight-forward and not obscure at all!
	 * thanks Spring Boot!
	 */
	@Bean
	public Jackson2RepositoryPopulatorFactoryBean repositoryPopulator() {
		Resource instrumentData = new ClassPathResource("instruments.json");
		Resource cdData = new ClassPathResource("cds.json");
		Jackson2RepositoryPopulatorFactoryBean factory = new Jackson2RepositoryPopulatorFactoryBean();
		factory.setResources(new Resource[] {instrumentData, cdData});
		return factory;
	}


}
