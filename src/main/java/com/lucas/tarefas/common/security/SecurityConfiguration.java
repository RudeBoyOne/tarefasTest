package com.lucas.tarefas.common.security;

import com.lucas.tarefas.api.security.FilterToken;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final FilterToken filterToken;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())

                .csrf()
                    .disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                    .authorizeHttpRequests()
                        .requestMatchers(HttpMethod.POST, "/login")
                            .permitAll()

                        .requestMatchers(HttpMethod.POST, "/usuarios/**")
                            .permitAll()

                        .requestMatchers(HttpMethod.GET, "/usuarios/**")
                            .hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.DELETE, "/usuarios/**")
                            .hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.PUT, "/usuarios/**")
                            .hasAnyRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/tarefas/**")
                            .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.GET, "/tarefas/**")
                            .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.DELETE, "/tarefas/**")
                            .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.PUT, "/tarefas/**")
                            .hasAnyRole("ADMIN", "USER")

                        .requestMatchers(HttpMethod.GET, "/docs/**")
                            .permitAll()

                        .requestMatchers(HttpMethod.GET, "/v3/api-docs/**")
                            .permitAll()

                        .anyRequest()
                            .authenticated()

                .and()
                    .addFilterBefore(filterToken, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowedOrigins(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
