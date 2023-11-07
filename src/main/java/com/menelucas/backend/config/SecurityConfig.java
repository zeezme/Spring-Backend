package com.menelucas.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.menelucas.backend.modules.auth.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
class SecurityConfig {

    private static final String[] WHITE_LIST_URL = {
            "/api/auth/**",
    };
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((req) ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                // .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())
                                // .requestMatchers(GET, "/teste").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                                // .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
                                // .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
                                // .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())
                                .requestMatchers(GET, "/api").hasAuthority("ROLE_" + USER.name())
                                .requestMatchers(POST, "/api/users/change-password").hasAuthority("ROLE_" + USER.name())
                                .requestMatchers(POST, "/api/forms/create").hasAuthority("ROLE_" + ADMIN.name())
                                .requestMatchers(POST, "/api/forms/insert-item").hasAuthority("ROLE_" + ADMIN.name())
                                .requestMatchers(DELETE, "/api/forms/delete-item/**").hasAuthority("ROLE_" + ADMIN.name())
                                .requestMatchers(DELETE, "/api/forms/delete-form/**").hasAuthority("ROLE_" + ADMIN.name())
                                .requestMatchers(POST, "/api/form/answer-item").hasAuthority("ROLE_" + USER.name())
                                .requestMatchers(GET, "/get-form-responses-by-user-and-form/**").hasAuthority("ROLE_" + USER.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


}

