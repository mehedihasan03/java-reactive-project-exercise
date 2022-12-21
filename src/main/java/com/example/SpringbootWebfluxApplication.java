package com.example;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
        title = "Spring Webflux CRUD Example",
        version = "1.0",
        description = "Learning Sample"
))
public class SpringbootWebfluxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebfluxApplication.class, args);
    }

}
