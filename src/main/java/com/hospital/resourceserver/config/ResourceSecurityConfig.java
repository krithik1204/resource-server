package com.hospital.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceSecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for REST APIs (important for Postman/mobile/frontend)
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**",
                                 "/api/profile/public/**",
                                 "/api/debug/**").permitAll()

                // USER endpoints
                .requestMatchers("/api/profile/**").hasRole("USER")

                // TEACHER endpoints
              //  .requestMatchers("/api/teacher/**").hasRole("TEACHER")
                .requestMatchers("/api/teacher/**").hasAuthority("ROLE_TEACHER")

                // mixed access
                .requestMatchers("/api/both/**").hasAnyRole("USER", "TEACHER")

                .anyRequest().authenticated()
            )

            // JWT Resource Server with proper role mapping
            .oauth2ResourceServer(oauth -> oauth
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

        return http.build();
    }

    /**
     * Converts JWT "roles" claim → Spring Security authorities
     * Example:
     *  "roles": ["ROLE_USER"] → GrantedAuthority("ROLE_USER")
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {

        JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();

        // IMPORTANT: tells Spring where roles are in JWT
        converter.setAuthoritiesClaimName("roles");

        // IMPORTANT: do NOT double prefix since your JWT already has ROLE_
        converter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(converter);

        return jwtConverter;
    }
}