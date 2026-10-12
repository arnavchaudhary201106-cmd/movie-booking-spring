package com.example.practice.SecurityConfig;

import com.example.practice.JwtFilter.JwtAuthenticationFilter;
import com.example.practice.service.CustomerUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

@Configuration
public class SecurityConfig {

    private final CustomerUserDetailsService customerDetailsService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            CustomerUserDetailsService customerDetailsService,
            JwtAuthenticationFilter jwtAuthenticationFilter) {

        this.customerDetailsService = customerDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/users/register",
                                "/users/assign-role",
                                "/users/token",
                                "/roles/**",
                                "/auth/login"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/movies").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/movies/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/movies/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/bookings").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT, "/bookings/**").hasRole("USER")

                        .requestMatchers(HttpMethod.GET, "/bookings").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE, "/bookings/**").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/shows").hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.GET,"/shows/**").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()

                );

        http.addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {

        DaoAuthenticationProvider daoAuthenticationProvider =
                new DaoAuthenticationProvider();

        daoAuthenticationProvider.setUserDetailsService(customerDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }
    }
