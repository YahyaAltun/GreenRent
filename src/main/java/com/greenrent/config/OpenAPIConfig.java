
package com.greenrent.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info=@Info(title="GreenRent API", version="1.0.0"),security=@SecurityRequirement(name="Bearer"))

@SecurityScheme(name="Bearer", type=SecuritySchemeType.HTTP,scheme="Bearer")
public class OpenAPIConfig {

}