package br.com.atividade.sistemabancario.Security.Config;

import br.com.atividade.sistemabancario.Security.jwt.AuthEntryPointJwt;
import br.com.atividade.sistemabancario.Security.jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private  JwtAuthenticationFilter jwtAuthenticationFilter;

    private AuthEntryPointJwt authEntryPointJwt;

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception  {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authEntryPointJwt))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated());

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
