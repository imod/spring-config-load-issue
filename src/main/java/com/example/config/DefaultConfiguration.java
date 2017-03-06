package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
// @formatter:off
@PropertySources({ 
	@PropertySource(value = "classpath:defaults.properties"), 
	@PropertySource(value = "classpath:user.properties", ignoreResourceNotFound = true)
})
// @formatter:on
public class DefaultConfiguration {

}
