package com.ecommerce.user.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(info=@Info(title="Usuário", description="Microsserviço de Usuário", version="v1", license=@License(name="MIT", url="http://localhost")))
public class SwaggerConfig {

}
