package com.filmyai.backend.security;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.unit.DataSize;

import jakarta.servlet.MultipartConfigElement;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize(DataSize.ofMegabytes(10));      // 10 MB
//        factory.setMaxRequestSize(DataSize.ofMegabytes(10));   // 10 MB
//        return factory.createMultipartConfig();
//    }
    
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable()) // // Disable CSRF for development
				.formLogin(httpForm -> httpForm
						.loginPage("/login")
						.defaultSuccessUrl("/index", true)
						.failureUrl("/login?error=true")
						.permitAll())

				.exceptionHandling(exception -> exception
						.accessDeniedPage("/error") // Custom error page for exceptions
				)

				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/index", "/api/auth/**", "/js/**", "/css/**", "/images/**", "/signup", "/forgot", "/profile", "/resetpassword", "/test/**", "/error")
						.permitAll().anyRequest().authenticated());
		return http.build();
	}
}
