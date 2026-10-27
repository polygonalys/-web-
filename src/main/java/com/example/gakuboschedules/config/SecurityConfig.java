package com.example.gakuboschedules.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.gakuboschedules.service.UserAccountDetailsService;

@Configuration 
@EnableWebSecurity 
public class SecurityConfig {
    private  final UserAccountDetailsService userAccountDetailsService;

    public SecurityConfig(UserAccountDetailsService userAccountDetailsService) {
        this.userAccountDetailsService = userAccountDetailsService;
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin(form -> form
            .usernameParameter("userId")
            .passwordParameter("userPassword")
            .loginPage("/login")
            .permitAll())
        .userDetailsService(userAccountDetailsService)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/").permitAll()
            .requestMatchers("/signup").permitAll()
            .requestMatchers("/user/**").hasRole("USER")
            .requestMatchers("/**").authenticated()
        )
        .logout(logout -> logout
            .logoutSuccessUrl("/login?logout")
            .permitAll()
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
