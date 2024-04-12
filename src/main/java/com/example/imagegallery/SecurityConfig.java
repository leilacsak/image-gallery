package com.example.imagegallery;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/api/galleries/**").permitAll()
                    .requestMatchers("/register", "/login").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin((form) -> form
                     .loginPage("/login")
                     .permitAll()
                )
                .logout((logout) -> logout
                    .logoutSuccessUrl("/")
                    .permitAll()
                )
                .csrf((csrf) -> csrf.disable());

        return http.build();
    }
}
