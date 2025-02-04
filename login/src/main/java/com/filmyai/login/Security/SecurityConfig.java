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

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    // It setups configuration for authentication, password encoding, and access rules

    private final MyAppUserService appUserService;

    
    private final CustomAuthenticationSuccessHandler successHandler;

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


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

         return httpSecurity
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(httpForm ->{
                httpForm
                .loginPage("/login")
                .successHandler(successHandler)  // Use the custom success handler
                .failureUrl("/login?error=true")
                .permitAll();

                
            })
    
            
            .authorizeHttpRequests(registry ->{
                registry.requestMatchers("/signup","/profile","/success", "/forgot", "/css/**", "/images/**", "/pdfs/**", "/js/**").permitAll();
                registry.anyRequest().authenticated();
            })
            .build();

    }
    
}