package com.example.geoapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Countries and Cities API")
                        .version("1.0.0")
                        .description("REST API for managing and querying Countries and Cities with pagination and OpenAPI documentation.")
                        .contact(new Contact().name("Backend Engineering")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Local Development Server")
                ));
    }
}
