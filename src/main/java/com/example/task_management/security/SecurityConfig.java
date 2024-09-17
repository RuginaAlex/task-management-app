package com.example.task_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Dezactivează CSRF doar pentru testare
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/users/**").authenticated() // Endpoint protejat
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults()); // Activează Basic Auth
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password("{noop}admin")  // {noop} pentru a nu folosi encoding pe parolă
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
