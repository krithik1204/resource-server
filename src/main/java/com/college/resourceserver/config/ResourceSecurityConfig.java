package com.college.resourceserver.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class ResourceSecurityConfig {

    @Bean
    public SecurityFilterChain security(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for REST APIs (important for Postman/mobile/frontend)
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .authorizeHttpRequests(auth -> auth
            		.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers("/api/public/**",
                                 "/api/profile/public/**",
                                 "/api/debug/**", "/swagger-ui/**",
                                 "/v3/api-docs/**","/v3/api-docs.yaml").permitAll()

                // USER endpoints
                .requestMatchers("/api/profile/**").hasRole("STUDENT")

                // TEACHER/FACULTY endpoints - Mark attendance
                .requestMatchers(HttpMethod.POST, "/api/attendance").hasAnyAuthority("ROLE_TEACHER", "ROLE_FACULTY")
                .requestMatchers(HttpMethod.PUT, "/api/attendance/**").hasAnyAuthority("ROLE_TEACHER", "ROLE_FACULTY")

                // PRINCIPAL endpoints - Create exams
                .requestMatchers(HttpMethod.POST, "/api/exams").hasAuthority("ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.PUT, "/api/exams/**").hasAuthority("ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.DELETE, "/api/exams/**").hasAuthority("ROLE_PRINCIPAL")

                // ADMIN endpoints
                .requestMatchers("/api/roles","/api/users/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/departments").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/departments/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/departments/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/courses").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/results").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/results/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/results/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/students").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/students/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/students/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/faculties").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/faculties").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/faculties/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/faculties/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, "/api/faculties/users").hasAuthority("ROLE_ADMIN")
                

                // EVENTS endpoints
                .requestMatchers(HttpMethod.POST, "/api/events").hasAnyAuthority("ROLE_ADMIN", "ROLE_TEACHER", "ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.PUT, "/api/events/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.DELETE, "/api/events/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")

                // COMPLAINTS endpoints
                .requestMatchers(HttpMethod.POST, "/api/complaints").hasAuthority("ROLE_STUDENT")
                .requestMatchers(HttpMethod.PUT, "/api/complaints/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_ADMIN", "ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.DELETE, "/api/complaints/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")

                // ANNOUNCEMENTS endpoints
                .requestMatchers(HttpMethod.POST, "/api/announcements").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.PUT, "/api/announcements/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")
                .requestMatchers(HttpMethod.DELETE, "/api/announcements/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_PRINCIPAL")

                // TEACHER endpoints
              //  .requestMatchers("/api/teacher/**").hasRole("TEACHER")
                .requestMatchers("/api/teacher/**").hasAuthority("ROLE_TEACHER")
           
                // GET endpoints - All authenticated users can read
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()

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
    
    /* =========================
    CORS CONFIGURATION
    =========================
  */
 @Bean
 public CorsConfigurationSource corsConfigurationSource() {
     CorsConfiguration configuration = new CorsConfiguration();
     configuration.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:5174"));
     configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
     configuration.setAllowedHeaders(List.of("Origin", "Authorization", "Content-Type", "Accept"));
     configuration.setAllowCredentials(true);

     UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
     source.registerCorsConfiguration("/**", configuration);
     return source;
 }
}