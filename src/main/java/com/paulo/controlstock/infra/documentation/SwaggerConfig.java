package com.paulo.controlstock.infra.documentation;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Rest - Control Stock")
                        .description("API para o controle de estoque")
                        .version("1.0.0")
                );
    }
}
