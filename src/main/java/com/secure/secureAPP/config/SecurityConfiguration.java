package com.secure.secureAPP.config;

import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.secure.secureAPP.user.Permission.*;
import static com.secure.secureAPP.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.anonymous;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true
)

public class SecurityConfiguration {

    private final AuthenticationConfiguration authenticationConfiguration;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private static final String[] WHITE_LIST_URL = {"/api/v1/auth/**"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/employee/**").hasAnyRole(ADMIN.name(), EMPLOYEE.name())
                                .requestMatchers(GET, "/api/v1/employee/**").hasAnyAuthority(ADMIN_READ.name(), EMPLOYEE_READ.name())
                                .requestMatchers(POST, "/api/v1/employee/**").hasAnyAuthority(ADMIN_CREATE.name(), EMPLOYEE_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/employee/**").hasAnyAuthority(ADMIN_UPDATE.name(), EMPLOYEE_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/employee/**").hasAnyAuthority(ADMIN_DELETE.name(), EMPLOYEE_DELETE.name())
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
