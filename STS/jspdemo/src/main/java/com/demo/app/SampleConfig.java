package com.demo.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// this annotation tells Spring that this is a Configuration class
@Configuration
public class SampleConfig implements WebMvcConfigurer{
	
	public void addViewControllers(ViewControllerRegistry registry) {
		
		// requests to /config will return the view "configpage"
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/config").setViewName("configpage");
		registry.addViewController("/mynotes").setViewName("notes");
		registry.addViewController("/implicit").setViewName("implicit");
		registry.addViewController("/implicit/request").setViewName("requestpage");
		registry.addViewController("/implicit/session").setViewName("sessionpage");
	}
}

