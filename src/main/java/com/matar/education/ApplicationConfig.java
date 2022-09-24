package com.matar.education;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer
{
	 private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
		    {
		        "classpath:/META-INF/resources/",
				 "classpath:/resources/",
		        "classpath:/static/", 
				"classpath:/public/",
				"classpath:/custom/",
				"file:/opt/myfiles/"
				
		    };

	 @Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 
	   registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/")
	           .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());	   
	 }
		    
		
		    
}
