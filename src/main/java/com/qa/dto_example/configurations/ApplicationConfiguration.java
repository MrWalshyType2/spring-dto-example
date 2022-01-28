package com.qa.dto_example.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // signifies to Spring that this is a config file
public class ApplicationConfiguration {

	@Bean // this signifies a component (a bean in the application context) that can be dependency injected
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
