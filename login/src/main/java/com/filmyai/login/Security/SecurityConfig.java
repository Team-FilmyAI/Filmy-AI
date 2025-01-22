package com.filmyai.login.Security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.filmyai.login.Model.MyAppUserService;



@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final MyAppUserService appUserService = new MyAppUserService();

    @Bean
    public UserDetailsService userDetailsService(){
        return appUserService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(appUserService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    
   /*
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .csrf(AbstractHttpConfigurer::disable) // Completely disable CSRF
            .formLogin(httpForm -> httpForm
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .authorizeHttpRequests(registry -> registry
                .requestMatchers("/login","/signup", "/forgot", "/reset-password", "/api/auth/**", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }
    
    */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(httpForm -> httpForm
                .loginPage("/login")
                .defaultSuccessUrl("/index", true)
                .failureUrl("/login?error=true")
                .permitAll()
            )
            .exceptionHandling(exception -> exception
                .accessDeniedPage("/error")  // Custom error page for exceptions
            )
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/test-email","/login", "/signup", "/forgot", "/reset-password", "/api/auth/**", "/css/**", "/images/**").permitAll()
                .anyRequest().authenticated()
            )
            .build();
    }

    
    
 


    
}