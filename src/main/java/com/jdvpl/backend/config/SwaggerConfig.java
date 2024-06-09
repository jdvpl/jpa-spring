package com.jdvpl.backend.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info=@Info(
            title = "API de Productos Unir",
            description = "API para administrar productos",
            version = "1.0.0"
    )
)
public class SwaggerConfig {

}
