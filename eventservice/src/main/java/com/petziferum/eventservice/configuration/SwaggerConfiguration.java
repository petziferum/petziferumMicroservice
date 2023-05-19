package com.petziferum.eventservice.configuration;

import com.petziferum.eventservice.event.Event;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName){
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new Info()
                        .version("1.0.0")
                        .title(appName)
                        .description("Events Backend")
                        .contact(new Contact()
                                .name("Max Mustermann")
                                .url("http://www.example.de")
                                .email("max.mustermann@muenchen.de")
                        )

                );
    }

    private Schema customSchema(Class className, String description){
        ResolvedSchema resolvedSchema = ModelConverters.getInstance()
                .resolveAsResolvedSchema(
                        new AnnotatedType(className).resolveAsRef(false));
        return resolvedSchema.schema.description(description).name("Hallihallo");
    }
}