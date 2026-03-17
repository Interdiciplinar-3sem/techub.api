package com.techub.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Classe de configuracao do Spring
public class SecurityConfig {

    @Bean // Registra as regras de seguranca HTTP
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable) // Desativa protecao CSRF (temporario)
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Permite todas as requisicoes
            .formLogin(AbstractHttpConfigurer::disable); // Desativa tela de login padrao

        return http.build(); // Aplica a configuracao
    }
}
