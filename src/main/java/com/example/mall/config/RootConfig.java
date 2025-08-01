package com.example.mall.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
	@Bean
	ModelMapper getMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration()
						.setFieldMatchingEnabled(true)
						.setFieldAccessLevel(AccessLevel.PRIVATE)
						.setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
}
