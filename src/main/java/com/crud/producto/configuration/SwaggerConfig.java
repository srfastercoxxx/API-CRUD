package com.crud.producto.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Bean
    public OpenAPI apiInfo() {
    		return new OpenAPI()
					.info(new Info()
    			    .title("api-producto")
    			    .description("Prueba de producto")
    			    .version("1.0.0")
                    .contact(new io.swagger.v3.oas.models.info.Contact()
							.email("admin@ssff.cl")));
    }

    /**
    ...
    ...
        Getters
        Setters
    ...
    ...
    **/
}
