package com.mallppang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mallppang.formatter.LocalDateFormatter;

@Configuration
public class CustomServletConfig implements WebMvcConfigurer {
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new LocalDateFormatter());
	}
}
