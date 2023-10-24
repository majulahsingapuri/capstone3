package com.example.capstone3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.requestMatchers("/logout", "/login").permitAll()
                        .requestMatchers("/", "/new", "/edit/*", "/delete/*", "/save", "/account/*", "/account/delete/*")
                        .authenticated())
                .formLogin(
                        fl -> fl.permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/login"))
                .csrf(csrf -> csrf.disable());

        return http.build();

    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
