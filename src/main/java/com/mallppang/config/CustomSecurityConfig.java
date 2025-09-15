package com.mallppang.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mallppang.security.filter.JWTCheckFilter;
import com.mallppang.security.handler.APILoginFailHandler;
import com.mallppang.security.handler.APILoginSuccessHandler;
import com.mallppang.security.handler.CustomAccessDeniedHandler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		log.info("------------------------------security config------------------------------");
		http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource()));
		http.sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.csrf(config -> config.disable());
		http.formLogin(config -> {
			config.loginPage("/member/login");
			config.successHandler(new APILoginSuccessHandler());	
			config.failureHandler(new APILoginFailHandler());
		});
		
		// 이거 설정 잘못하면 프론트 엑시오스 에러남
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(
						"/member/login",
						"/member/auth/**",
						"/f/**",
						"/fc/**",
						"/r/**",
						"/rc/**"
						).permitAll()
				.anyRequest().authenticated())
		.cors(cors -> cors.configurationSource(corsConfigurationSource()));
		
		http.addFilterBefore(new JWTCheckFilter(), UsernamePasswordAuthenticationFilter.class);
		http.exceptionHandling(config -> config.accessDeniedHandler(new CustomAccessDeniedHandler()));
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		// 배포 확정되면 * 대신 도메인 이름을 적어주는 것이 더 좋을 수 있음
		configuration.setAllowedOriginPatterns(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
		configuration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
