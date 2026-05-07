package com.college.resourceserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Resource Server API")
                        .version("1.0.0")
                        .description("College Resource Server API Documentation")
                        .contact(new Contact()
                                .name("College Admin")
                                .email("admin@college.edu")));
    }
}