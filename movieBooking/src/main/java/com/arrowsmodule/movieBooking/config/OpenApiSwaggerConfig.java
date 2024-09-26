package com.arrowsmodule.movieBooking.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiSwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();
    }
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Ticket Booking Backend API")
                .description("API for creating to operate on ticket booking features")
                .version("1.0")
                .contact(apiContact());
    }

    private Contact apiContact() {
        return new Contact()
                .name("Arrows Module")
                .email("arrows.module@gmail.com")
                .url("https://github.com/");
    }

}
