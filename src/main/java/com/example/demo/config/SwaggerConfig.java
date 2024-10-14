package com.example.demo.config;

import com.example.demo.jwt.JwtProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.example.demo.config
 * fileName       : SwaggerConfig
 * author         : JAEIK
 * date           : 10/7/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/7/24        JAEIK       최초 생성
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(Type.APIKEY)
                .in(In.HEADER)
                .name(JwtProperties.ACCESS_TOKEN_HEADER);

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement)
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("Spring Boot Rest API 4YClingHomeRun")
                .description("4YClingHomeRun")
                .version("1.0.0");
    }

}
