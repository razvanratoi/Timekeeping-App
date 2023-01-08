package com.example.timekeeping;

import com.example.timekeeping.model.Client;
import com.example.timekeeping.model.User;
import com.example.timekeeping.repo.ClientRepo;
import com.example.timekeeping.repo.UserRepo;
import com.example.timekeeping.service.impl.ClientServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class TimekeepingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TimekeepingApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ClientRepo clientRepo, ClientServiceImpl clientService) {
		return args -> {
		};
	}

	@Bean
	public CorsFilter corsFilter(){
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		CorsFilter corsFilter = new CorsFilter();
		return new CorsFilter();
	}

	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/user/list").allowedOrigins("http://localhost:8080");
			}
		};
	}

	@Component
	public class CorsFilter extends OncePerRequestFilter {

		@Override
		protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response,
										final FilterChain filterChain) throws ServletException, IOException {
			//response.addHeader("Access-Control-Allow-Origin", "");
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, PATCH, HEAD");
			response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
			response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials");
			response.addHeader("Access-Control-Allow-Credentials", "true");
			response.addIntHeader("Access-Control-Max-Age", 10);
			filterChain.doFilter(request, response);
		}

	}
}
