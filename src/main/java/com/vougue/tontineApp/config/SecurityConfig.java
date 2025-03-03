package com.vougue.tontineApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuration des règles de sécurité
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Désactiver CSRF (nécessaire pour les API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Autoriser l'accès public à l'authentification
                        .anyRequest().authenticated() // Toutes les autres requêtes nécessitent une authentification
                )
                .httpBasic(); // Utiliser l'authentification de base (Basic Auth)

        return http.build();
    }

    // Configuration d'un utilisateur en mémoire (pour les tests)
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password")) // Encoder le mot de passe
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    // Configuration de l'encodeur de mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}