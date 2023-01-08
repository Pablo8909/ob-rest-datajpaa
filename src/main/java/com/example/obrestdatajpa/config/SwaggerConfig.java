package com.example.obrestdatajpa.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import java.util.Collections;

/**
 * localhost:8080/swagger-ui/
 */

@Configuration
public class SwaggerConfig {

    // Se crea el metodo para iniciar el servico Swagger

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    // Creando el metodo para el apiInfo()

    private ApiInfo apiDetails(){

        return new ApiInfo("Spring Boot Boot Api RESET",
                "Library Api rest docs",
                "1.0",
                "http://www.google.com",
                new Contact("Pablo","http://www.google.com","pablotrujillojuvier@gmail.com"),
                "MIT","http://www.google.com",
                Collections.emptyList());

    }

}
