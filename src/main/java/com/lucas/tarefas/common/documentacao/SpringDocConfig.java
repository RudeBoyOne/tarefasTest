package com.lucas.tarefas.common.documentacao;

import com.lucas.tarefas.api.exception.handler.Problema;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SpringDocConfig {

    private static final String badRequestResponse = "BadRequestResponse";
    private static final String notFoundResponse = "NotFoundResponse";
    private static final String internalServerErrorResponse = "InternalServerErrorResponse";

    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API RESTful - Tarefas teste")
                        .version("1.0.0")
                        .description("API RESTful de gerenciamento de tarefas")
                        .contact(new Contact()
                                .name("Lucas Ferreira Nogueira")
                                .url("https://github.com/RudeBoyOne")
                                .email("lucas.ferreiranogueira@outlook.com")))

                .tags(List.of(
                        new Tag().name("Auth").description("API de autitenticação de usuários na aplicação"),
                        new Tag().name("Usuários").description("Gerencia os usuários da aplicação")
                ))
                .components(
                        new Components()
                                .schemas(gerarSchemas())
                                .responses(gerarResponses())
                );
    }

    @Bean
    public OpenApiCustomizer openApiCustomiser() {
        return baseOpenApi -> {
            baseOpenApi.getPaths()
                    .values()
                    .forEach(pathItem -> pathItem.readOperationsMap()
                            .forEach((httpMethod, operation) -> {
                                ApiResponses responses = operation.getResponses();
                                switch (httpMethod) {
                                    case GET, DELETE -> {
                                                responses.addApiResponse("404",
                                                new ApiResponse().$ref(notFoundResponse));
                                    }
                                    case POST, PUT -> {
                                        responses.addApiResponse("404", new ApiResponse().$ref(notFoundResponse));
                                        responses.addApiResponse("400",
                                                new ApiResponse().$ref(badRequestResponse));
                                    }
                                    default ->
                                            responses.addApiResponse("500", new ApiResponse().$ref(internalServerErrorResponse));
                                }
                            })
                    );
        };
    }

    private Map<String, Schema> gerarSchemas() {
        final Map<String, Schema> schemaMap = new HashMap<>();

        Map<String, Schema> problemSchema = ModelConverters.getInstance().read(Problema.class);
        Map<String, Schema> problemCampoSchema = ModelConverters.getInstance().read(Problema.Campo.class);

        schemaMap.putAll(problemSchema);
        schemaMap.putAll(problemCampoSchema);

        return schemaMap;
    }

    private Map<String, ApiResponse> gerarResponses() {
        final Map<String, ApiResponse> apiResponseMap = new HashMap<>();

        Content content = new Content()
                .addMediaType(APPLICATION_JSON_VALUE,
                        new MediaType().schema(new Schema<Problema>().$ref("Problema")));

        apiResponseMap.put(badRequestResponse, new ApiResponse()
                .description("Bad Request")
                .content(content));

        apiResponseMap.put(notFoundResponse, new ApiResponse()
                .description("Not Found")
                .content(content));


        apiResponseMap.put(internalServerErrorResponse, new ApiResponse()
                .description("Internal Server Error Response")
                .content(content));

        return apiResponseMap;
    }

}