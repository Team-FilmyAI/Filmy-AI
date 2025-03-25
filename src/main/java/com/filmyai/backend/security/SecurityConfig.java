package com.filmyai.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

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
						.requestMatchers("/api/auth/**", "/js/**", "/css/**", "/images/**", "/signup", "/forgot", "/profile", "/resetpassword")
						.permitAll().anyRequest().authenticated());
		return http.build();
	}
}
