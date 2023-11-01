package com.cmcba.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi openApi(){
        return GroupedOpenApi.builder()
                .group("CMCBA")
                .packagesToScan("com.cmcba")
                .build();
    }

    @Bean
    public OpenAPI springOpenApi(){
        String version = "1.0";
        return new OpenAPI()
                .info(new Info().title("CMCBA - modulo clientes")
                        .description("Modulo de clientes y autenticacion")
                        .contact(new Contact().name("caito").email("caitocd@gmail.com"))
                        .version(version));
    }
}
