package com.example.sportsbuddy;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@SpringBootApplication
@EnableWebMvc
public class SportsbuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsbuddyApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("*")); // You can replace "*" with specific allowed origins
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With"));
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString("http://localhost:5173");
		configuration.setExposedHeaders(Collections.singletonList(builder.build().toUriString()));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	
}
